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
public class CardFrameParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String cardId;
    @Size(min = 6, message = TribeParamError.MIN_LENGTH)
    @Size(max = 12, message = TribeParamError.MAX_LENGTH)
    private String page;

}
