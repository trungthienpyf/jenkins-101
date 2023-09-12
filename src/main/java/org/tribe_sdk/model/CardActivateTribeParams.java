package org.tribe_sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CardActivateTribeParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String cardId;
    @Size(min = 16, message = TribeParamError.MIN_LENGTH)
    @Size(max = 19, message = TribeParamError.MAX_LENGTH)
    private String cardPan;
    @Size(min = 2, message = TribeParamError.MIN_LENGTH)
    @Size(max = 2, message = TribeParamError.MAX_LENGTH)
    private String cardExpirationMonth;
    @Size(min = 4, message = TribeParamError.MIN_LENGTH)
    @Size(max = 4, message = TribeParamError.MAX_LENGTH)
    private String cardExpirationYear;
    @JsonProperty("card_cv2")
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String cardCvc2;
}
