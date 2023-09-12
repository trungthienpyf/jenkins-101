package org.tribe_sdk.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CreatePepEventParams {
    private String identifier;
    private CreateHolderPepParams data;
}
