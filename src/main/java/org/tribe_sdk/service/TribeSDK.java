package org.tribe_sdk.service;

import org.tribe_sdk.model.TribeResponse;

import java.util.Map;

public interface TribeSDK {

    TribeResponse createCard(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse activateCard(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse changeCardStatus(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse setCardPin(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse getCardPin(String cardId, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse loadCard(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse getBalance(String cardId, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse getTransactions(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse createAccount(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse updateHolder(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse createNewHolder(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse createCardFrame(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse getAccounts(String accountId, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse sendPepSanctionScreening(String requestBody, Map<String, String> listHeaders) throws Exception;

    TribeResponse sendConfirmAuthentication(String requestBody, Map<String, String> listHeaders, String correlationId) throws Exception;

    TribeResponse getCardCvc(String cardId, Map<String, String> listHeader, String correlationId) throws Exception;
}
