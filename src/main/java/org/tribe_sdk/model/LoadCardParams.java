package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class LoadCardParams extends BaseParam {
    private String cardId;
    private String accountId;
    @NotNull(message = TribeParamError.NOT_NULL)
    private int amount;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String currencyIson;
    private String loadType;
    private String loadSource;

}