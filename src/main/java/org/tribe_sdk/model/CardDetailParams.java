package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CardDetailParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    private String cardId;
    private Integer accessLevel;
}
