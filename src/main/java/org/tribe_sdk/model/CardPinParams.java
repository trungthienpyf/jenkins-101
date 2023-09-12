package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CardPinParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String cardId;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 4, message = TribeParamError.MIN_LENGTH)
    @Size(max = 4, message = TribeParamError.MAX_LENGTH)
    private String pin;
}
