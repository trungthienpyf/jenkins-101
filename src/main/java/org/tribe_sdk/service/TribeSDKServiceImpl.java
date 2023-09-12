package org.tribe_sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.tribe_sdk.exception.TribeException;
import org.tribe_sdk.model.*;
import org.tribe_sdk.repository.ManagerLogRepository;
import org.tribe_sdk.util.Actions;
import org.tribe_sdk.util.RequestUtil;
import org.tribe_sdk.validator.TribeValidator;

import java.util.Map;

@Slf4j
public class TribeSDKServiceImpl implements TribeSDK {
    private ManagerLogRepository managerLogRepository;
    private RequestUtil requestUtil;
    private ObjectMapper mapper = new ObjectMapper();
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
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

    @Override
    public TribeResponse createCard(String cardCreateParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardCreateParams cardCreateRequest = convertStringJsonToModel(cardCreateParams, CardCreateParams.class);
        TribeValidator.validationPrams(cardCreateRequest);
        return requestUtil.postRequest(cardCreateRequest, listHeader, Actions.CREATE_CARD, correlationId);
    }

    @Override
    public TribeResponse activateCard(String cardActivateTribeParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardActivateTribeParams cardActivateTribeRequest = convertStringJsonToModel(cardActivateTribeParams, CardActivateTribeParams.class);
        TribeValidator.validationPrams(cardActivateTribeRequest);
        return requestUtil.postRequest(cardActivateTribeRequest, listHeader, Actions.ACTIVE_CARD, correlationId);
    }

    @Override
    public TribeResponse changeCardStatus(String cardStatusParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardStatusParams cardStatusRequest = convertStringJsonToModel(cardStatusParams, CardStatusParams.class);
        TribeValidator.validationPrams(cardStatusRequest);
        return requestUtil.postRequest(cardStatusRequest, listHeader, Actions.CHANGE_CARD_STATUS, correlationId);

    }

    @Override
    public TribeResponse setCardPin(String cardPinParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardPinParams cardPinRequest = convertStringJsonToModel(cardPinParams, CardPinParams.class);
        TribeValidator.validationPrams(cardPinRequest);
        return requestUtil.postRequest(cardPinRequest, listHeader, Actions.SET_CARD_PIN, correlationId);

    }

    @Override
    public TribeResponse getCardPin(String cardId, Map<String, String> listHeader, String correlationId) throws Exception {
        CardDetailParams cardDetailParams = new CardDetailParams();
        cardDetailParams.setCardId(cardId);
        TribeValidator.validationPrams(cardDetailParams);
        return requestUtil.postRequest(cardDetailParams, listHeader, Actions.GET_CARD_PIN, correlationId);
    }

    @Override
    public TribeResponse loadCard(String loadCardParams, Map<String, String> listHeader, String correlationId) throws Exception {
        LoadCardParams loadCardRequest = convertStringJsonToModel(loadCardParams, LoadCardParams.class);
        TribeValidator.validationPrams(loadCardRequest);
        return requestUtil.postRequest(loadCardRequest, listHeader, Actions.LOAD_CARD, correlationId);
    }

    @Override
    public TribeResponse getBalance(String cardId, Map<String, String> listHeader, String correlationId) throws Exception {
        CardDetailParams cardDetailParams = new CardDetailParams();
        cardDetailParams.setCardId(cardId);
        TribeValidator.validationPrams(cardDetailParams);
        return requestUtil.postRequest(cardDetailParams, listHeader, Actions.GET_BALANCE, correlationId);

    }

    @Override
    public TribeResponse getTransactions(String cardTransactionsParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardTransactionsParams cardTransactionsRequest = convertStringJsonToModel(cardTransactionsParams, CardTransactionsParams.class);
        TribeValidator.validationPrams(cardTransactionsRequest);
        return requestUtil.postRequest(cardTransactionsRequest, listHeader, Actions.GET_CARD_TRANSACTIONS, correlationId);
    }

    @Override
    public TribeResponse createAccount(String accountCreateParams, Map<String, String> listHeader, String correlationId) throws Exception {
        AccountCreateParams accountCreateRequest = convertStringJsonToModel(accountCreateParams, AccountCreateParams.class);
        TribeValidator.validationPrams(accountCreateRequest);
        return requestUtil.postRequest(accountCreateRequest, listHeader, Actions.CREATE_ACCOUNT, correlationId);
    }

    @Override
    public TribeResponse updateHolder(String holderUpdateParams, Map<String, String> listHeader, String correlationId) throws Exception {
        HolderUpdateParams holderUpdateRequest = convertStringJsonToModel(holderUpdateParams, HolderUpdateParams.class);
        TribeValidator.validationPrams(holderUpdateRequest);
        return requestUtil.postRequest(holderUpdateRequest, listHeader, Actions.UPDATE_HOLDER, correlationId);
    }

    @Override
    public TribeResponse createNewHolder(String createHolderParams, Map<String, String> listHeader, String correlationId) throws Exception {
        HolderCreateParams createHolderRequest = convertStringJsonToModel(createHolderParams, HolderCreateParams.class);
        TribeValidator.validationPrams(createHolderRequest);
        return requestUtil.postRequest(createHolderRequest, listHeader, Actions.CREATE_HOLDER, correlationId);
    }

    @Override
    public TribeResponse createCardFrame(String cardFrameParams, Map<String, String> listHeader, String correlationId) throws Exception {
        CardFrameParams cardFrameRequest = convertStringJsonToModel(cardFrameParams, CardFrameParams.class);
        TribeValidator.validationPrams(cardFrameRequest);
        return requestUtil.postRequest(cardFrameRequest, listHeader, Actions.CREATE_CARD_FRAME, correlationId);

    }

    @Override
    public TribeResponse getAccounts(String accountId, Map<String, String> listHeader, String correlationId) throws Exception {
        AccountsDetailParams accountsDetailParams = new AccountsDetailParams();
        accountsDetailParams.setAccountId(accountId);
        return requestUtil.postRequest(accountsDetailParams, listHeader, Actions.GET_ACCOUNT, correlationId);
    }

    @Override
    public TribeResponse sendPepSanctionScreening(String createPepRequest, Map<String, String> listHeader) throws Exception {

        CreatePepEventParams createPepEventParams = convertStringJsonToModel(createPepRequest, CreatePepEventParams.class);
        CreateHolderPepParams createHolderPepParams = convertModelToModel(createPepEventParams.getData(), CreateHolderPepParams.class);
        TribeValidator.validationPrams(createHolderPepParams);
        return requestUtil.eventPepScreeningPost(createPepEventParams, listHeader);
    }

    @Override
    public TribeResponse sendConfirmAuthentication(String createPepRequest, Map<String, String> listHeader, String correlationId) throws Exception {
        ConfirmAuthenticationParams createPepEventParams = convertStringJsonToModel(createPepRequest, ConfirmAuthenticationParams.class);
        TribeValidator.validationPrams(createPepEventParams);
        return requestUtil.postRequest(createPepEventParams, listHeader, Actions.AUTHENTICATION_CONFIRM, correlationId);
    }

    @Override
    public TribeResponse getCardCvc(String cardId, Map<String, String> listHeader, String correlationId) throws Exception {
        CardDetailParams cardDetailParams = new CardDetailParams();
        cardDetailParams.setCardId(cardId);
        TribeValidator.validationPrams(cardDetailParams);
        return requestUtil.postRequest(cardDetailParams, listHeader, Actions.GET_CVC, correlationId);
    }

    private <T> T convertStringJsonToModel(String request, Class<T> model) {
        try {
            return mapper.readValue(request, model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new TribeException(ex.getMessage());
        }
    }

    private <T, L> T convertModelToModel(L request, Class<T> model) {
        try {
            return mapper.convertValue(request, model);
        } catch (Exception ex) {

            throw new TribeException(ex.getMessage());
        }
    }

    public static TribeSDKServiceImplBuilder builder() {
        return new TribeSDKServiceImplBuilder();
    }

    public TribeSDKServiceImpl(final String dbUrl, final String dbUsername, final String dbPassword, final String tribeProgramUrl, final String accessKey, final String accessApiId, final String accessApiKey, final String dataEncryptionKey, final String version, final String privateKey, final String publicKey, final Boolean encryptMode, final String pepSanctionUrl, final String pepAccessToken, final String pepAuthToken) {
        requestUtil = new RequestUtil(dbUrl, dbUsername, dbPassword, tribeProgramUrl, accessKey, accessApiId, accessApiKey, dataEncryptionKey, version, privateKey, publicKey, encryptMode, pepSanctionUrl, pepAccessToken, pepAuthToken);
    }

    public static class TribeSDKServiceImplBuilder {
        private String dbUrl;
        private String dbUsername;
        private String dbPassword;
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

        TribeSDKServiceImplBuilder() {
        }


        public TribeSDKServiceImplBuilder dbUrl(final String dbUrl) {
            this.dbUrl = dbUrl;
            return this;
        }

        public TribeSDKServiceImplBuilder dbUsername(final String dbUsername) {
            this.dbUsername = dbUsername;
            return this;
        }

        public TribeSDKServiceImplBuilder dbPassword(final String dbPassword) {
            this.dbPassword = dbPassword;
            return this;
        }

        public TribeSDKServiceImplBuilder tribeProgramUrl(final String tribeProgramUrl) {
            this.tribeProgramUrl = tribeProgramUrl;
            return this;
        }

        public TribeSDKServiceImplBuilder accessKey(final String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public TribeSDKServiceImplBuilder accessApiId(final String accessApiId) {
            this.accessApiId = accessApiId;
            return this;
        }

        public TribeSDKServiceImplBuilder accessApiKey(final String accessApiKey) {
            this.accessApiKey = accessApiKey;
            return this;
        }

        public TribeSDKServiceImplBuilder dataEncryptionKey(final String dataEncryptionKey) {
            this.dataEncryptionKey = dataEncryptionKey;
            return this;
        }

        public TribeSDKServiceImplBuilder version(final String version) {
            this.version = version;
            return this;
        }

        public TribeSDKServiceImplBuilder privateKey(final String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public TribeSDKServiceImplBuilder publicKey(final String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public TribeSDKServiceImplBuilder encryptMode(final Boolean encryptMode) {
            this.encryptMode = encryptMode;
            return this;
        }

        public TribeSDKServiceImplBuilder pepSanctionUrl(final String pepSanctionUrl) {
            this.pepSanctionUrl = pepSanctionUrl;
            return this;
        }

        public TribeSDKServiceImplBuilder pepAccessToken(final String pepAccessToken) {
            this.pepAccessToken = pepAccessToken;
            return this;
        }

        public TribeSDKServiceImplBuilder pepAuthToken(final String pepAuthToken) {
            this.pepAuthToken = pepAuthToken;
            return this;
        }

        public TribeSDKServiceImpl build() {

            return new TribeSDKServiceImpl(this.dbUrl, this.dbUsername, this.dbPassword, this.tribeProgramUrl, this.accessKey, this.accessApiId, this.accessApiKey, this.dataEncryptionKey, this.version, this.privateKey, this.publicKey, this.encryptMode, this.pepSanctionUrl, this.pepAccessToken, this.pepAuthToken);
        }

    }
}
