package org.tribe_sdk.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.tribe_sdk.exception.TribeException;
import org.tribe_sdk.model.TribeResponse;
import org.tribe_sdk.repository.JdbcManagerLogRepository;
import org.tribe_sdk.repository.ManagerLog;
import org.tribe_sdk.repository.ManagerLogRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Getter
@Setter
public class RequestUtil {
    private final ObjectMapper mapper = new ObjectMapper();
    ManagerLogRepository managerLogRepository;
    private static final String EXTERNAL_PROVIDER_CODE = "TRIBE";
    private static final String EXTERNAL_ACTION_PEP = "createEventDataPep";
    private String tribeProgramUrl;
    private String accessKey;
    private String accessApiId;
    private String accessApiKey;
    private String dataEncryptionKey;
    private String version;
    private String privateKey;
    private String publicKey;
    private Boolean encryptMode;
    private String pepSanctionUrl;
    private String pepAccessToken;
    private String pepAuthToken;

    public RequestUtil(String url, String username, String password,
                       String tribeProgramUrl, String accessKey, String accessApiId, String accessApiKey,
                       String dataEncryptionKey, String version, String privateKey, String publicKey, Boolean encryptMode,
                       String pepSanctionUrl, String pepAccessToken, String pepAuthToken) {
        this.managerLogRepository = new JdbcManagerLogRepository(url, username, password);
        this.tribeProgramUrl = tribeProgramUrl;
        this.accessKey = accessKey;
        this.accessApiId = accessApiId;
        this.accessApiKey = accessApiKey;
        this.dataEncryptionKey = dataEncryptionKey;
        this.version = version;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.encryptMode = encryptMode;
        this.pepSanctionUrl = pepSanctionUrl;
        this.pepAccessToken = pepAccessToken;
        this.pepAuthToken = pepAuthToken;

        notNull(version, "version");
        notNull(tribeProgramUrl, "tribeProgramUrl");
        notNull(accessKey, "accessKey");
        notNull(accessApiId, "accessApiId");
        notNull(accessApiKey, "accessApiKey");
        if (!encryptMode) {
            notNull(this.privateKey, "privateKey");
            notNull(this.publicKey, "privateKey");
            notNull(this.dataEncryptionKey, "dataEncryptionKey");
        }
    }

    public <T, M> TribeResponse postRequest(T params, Map<String, String> listHeader, M actions, String referenceId) throws Exception {

        HttpPost request = new HttpPost(tribeProgramUrl);

        for (Map.Entry<String, String> entry : listHeader.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
        String stringModel = addActionToBody(mapper.writeValueAsString(params), actions);
        String body = convertModelToString(stringModel, actions, referenceId, request);
        StringEntity entity = new StringEntity(body, "UTF-8");
        request.setEntity(entity);
        ManagerLog managerLog = createManagerLog(stringModel, actions, convertListHeaderToJsonString(request.getAllHeaders()), referenceId, tribeProgramUrl);
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
        return parseToTribeResponse(client.execute(request), managerLog);
    }

    private <T, M> String convertModelToString(String params, M actions, String referenceId, HttpPost request) {
        try {
            HashMap<String, Object> model = mapper.readValue(params, new TypeReference<HashMap<String, Object>>() {});
            if (encryptMode) {
                request.setHeader("Content-Type", "text/plain");
                request.setHeader("x-access-key", accessKey);
                request.setHeader("x-access-api-id", accessApiId);
                request.setHeader("x-access-api-key", accessApiKey);
                request.setHeader("x-action", actions.toString());
                request.setHeader("x-api-version", version);
                request.setHeader("x-encryption", "1");
                return encryptMessage(mapper.writeValueAsString(model), request);
            }

            model.put("action", actions.toString());
            model.put("access_key", accessKey);
            model.put("access_api_id", accessApiId);
            model.put("access_api_key", accessApiKey);
            model.put("api_version", version);
            model.put("request_reference_id", referenceId);
            return mapper.writeValueAsString(model);
        } catch (Exception ex) {
            log.error("Mapper model to json error  " + ex.getMessage());
            throw new TribeException(ex.getMessage());
        }
    }

    public <T> TribeResponse eventPepScreeningPost(T params, Map<String, String> listHeader) throws Exception {
        HttpPost request = new HttpPost(pepSanctionUrl);
        String contentParams = mapper.writeValueAsString(params);
        encryptHeader(contentParams, listHeader);
        for (Map.Entry<String, String> entry : listHeader.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }

        StringEntity entity = new StringEntity(contentParams, "UTF-8");
        request.setEntity(entity);
        String contentRequest = EntityUtils.toString(request.getEntity(), "UTF-8");
        ManagerLog managerLog = createManagerLog(contentRequest, EXTERNAL_ACTION_PEP, convertListHeaderToJsonString(request.getAllHeaders()), null, pepSanctionUrl);
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
        return parseToTribeResponse(client.execute(request), managerLog);
    }

    private <T> String addActionToBody(String params, T actions) {
        try {
            HashMap<String, Object> model = mapper.readValue(params, new TypeReference<HashMap<String, Object>>() {
            });
            model.put("action", actions.toString());
            return mapper.writeValueAsString(model);
        } catch (Exception ex) {
            log.error("Mapper model to json error  " + ex.getMessage());
            throw new TribeException(ex.getMessage());
        }
    }

    private void encryptHeader(String requestContent, Map<String, String> listHeader) {

        long signatureTimestamp = System.currentTimeMillis() / 1000;
        String signatureHash = SignatureHash.encryptSignature(pepAccessToken + requestContent + signatureTimestamp);
        listHeader.put("x-auth-token", pepAuthToken);
        listHeader.put("x-auth-signature", signatureHash);
        listHeader.put("x-auth-signature-timestamp", String.valueOf(signatureTimestamp));
    }

    private TribeResponse parseToTribeResponse(HttpResponse response, ManagerLog managerLog) {
        TribeResponse tribeResponse = new TribeResponse();
        tribeResponse.setHeader(convertListHeaderToJsonString(response.getAllHeaders()));
        if (response.getEntity() != null) {
            try {
                if (encryptMode) {
                    tribeResponse.setBody(decryptMessage(EntityUtils.toString(response.getEntity(), "UTF-8"), response.getFirstHeader("x-sign")));
                } else {
                    tribeResponse.setBody(decryptMessage(EntityUtils.toString(response.getEntity(), "UTF-8"), null));

                }
            } catch (Exception ex) {
                log.error("Encrypt fail " + ex.getMessage());
                throw new TribeException(ex.getMessage());
            }
        }
        tribeResponse.setCode(response.getStatusLine().getStatusCode());
        updateManagerLog(managerLog, tribeResponse.getBody(),
                tribeResponse.getCode(), tribeResponse.getHeader());
        return tribeResponse;
    }

    private String convertListHeaderToJsonString(Header[] headers) {
        Map<String, String> listHeaders = new HashMap<>();
        for (Header header : headers) {
            listHeaders.put(header.getName(), header.getValue());
        }
        try {
            return mapper.writeValueAsString(listHeaders);
        } catch (Exception ex) {
            log.error("Mapper model to json error  " + ex.getMessage());
            throw new TribeException(ex.getMessage());
        }

    }

    private <T> ManagerLog createManagerLog(String requestBody, T action, String listHeaders, String correlationId, String url) {
        ManagerLog managerLog = new ManagerLog();
        managerLog.setCorrelationId(correlationId);
        managerLog.setRequestId(UUID.randomUUID().toString());
        managerLog.setRequestUrl(url);
        managerLog.setExternalProviderCode(EXTERNAL_PROVIDER_CODE);
        managerLog.setSent(true);
        managerLog.setRequestDate(DateUtil.convertToUTC(new Date()));
        managerLog.setRequestMessage(requestBody);
        managerLog.setExternalAction(action.toString());
        managerLog.setRequestHeader(listHeaders);
        managerLogRepository.save(managerLog);

        return managerLog;
    }

    private void updateManagerLog(ManagerLog updateLog, String responseBody, int code, String responseHeader) {
        updateLog.setSuccess(true);
        updateLog.setResponseDate(DateUtil.convertToUTC(new Date()));
        updateLog.setResponseMessage(responseBody);
        updateLog.setResponseHttpCode(code);
        updateLog.setResponseHeader(responseHeader);
        managerLogRepository.update(updateLog);
    }

    private String encryptMessage(String body, HttpPost request) throws Exception {
        String secret = UUID.randomUUID().toString().replace("-", "");
        String sign = CryptoUtil.encryptSecret(CryptoUtil.convertStringToPrivateKey(privateKey), secret);
        request.setHeader("x-sign", sign);
        return CryptoUtil.encryptContent(body, secret);

    }


    private String decryptMessage(String body, Header sign) {

        if (sign == null) return body;
        try {
            String decryptSecret = CryptoUtil.decryptSecret(CryptoUtil.convertStringToPublicKey(publicKey), sign.getValue());
            String dataDecrypted = CryptoUtil.decryptContent(body, decryptSecret);
            return getDataSensitive(dataDecrypted);
        } catch (Exception ex) {
            log.error("Decrypt fail  " + ex.getMessage());
            throw new TribeException(ex.getMessage());
        }

    }

    private String getDataSensitive(String dataDecrypted) throws Exception {
        JSONObject jsonObject = new JSONObject(dataDecrypted);

        setToResponse(jsonObject, readData(dataDecrypted, "$.data.pin"), "pin");
        setToResponse(jsonObject, readData(dataDecrypted, "$.data.pan"), "pan");
        setToResponse(jsonObject, readData(dataDecrypted, "$.data.cvc2"), "cvc2");
        setToResponse(jsonObject, readData(dataDecrypted, "$.data.acs_password"), "acs_password");
        return jsonObject.toString();
    }

    private void setToResponse(JSONObject jsonObject, String value, String key) throws Exception {
        if (value != null && !value.trim().isEmpty() && jsonObject.has("data")) {
            jsonObject.getJSONObject("data").put(key, CryptoUtil.decryptDataSensitive(value, dataEncryptionKey));
        }
    }

    private String readData(String responseBody, String jsonPath) {
        try {
            return JsonPath.read(responseBody, jsonPath);
        } catch (Exception e) {
            return null;
        }
    }

    private void notNull(Object obj, String key) {
        if (obj == null) {
            throw new TribeException("Field " + key + " not be null");
        }
    }
}
