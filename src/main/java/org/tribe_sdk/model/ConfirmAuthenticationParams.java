package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;

@ToString
@Setter
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class ConfirmAuthenticationParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    private Long authRequestId;
    @NotNull(message = TribeParamError.NOT_NULL)
    private String confirmationStatus;
    @NotNull(message = TribeParamError.NOT_NULL)
    private Integer oobAuthMethod;
    private String validationValue;

}
