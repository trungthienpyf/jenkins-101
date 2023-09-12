package org.tribe_sdk.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TribeException extends RuntimeException {


    public TribeException(String message) {
        super(message);

    }
}
