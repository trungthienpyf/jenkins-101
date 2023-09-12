package org.tribe_sdk.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLog {
    private String requestId;
    private String correlationId;
    private String requestUrl;
    private String externalAction;
    private String externalProviderCode;
    private String requestMeta;
    private String requestHeader;
    private String requestMessage;
    private Date requestDate;
    private String responseMessage;
    private String responseHeader;
    private int responseHttpCode;
    private Date responseDate;
    private Long duration;
    private boolean isSent;
    private boolean isSuccess;
}
