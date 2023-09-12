package org.tribe_sdk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TribeResponse {
    private String header;
    private String body;
    private Integer code;
}
