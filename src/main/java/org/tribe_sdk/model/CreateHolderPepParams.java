package org.tribe_sdk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;

@ToString
@Setter
@Getter
public class CreateHolderPepParams {
    @NotNull(message = TribeParamError.NOT_NULL)
    private String userid;
    @NotNull(message = TribeParamError.NOT_NULL)
    private String firstName;
    private String middleName;
    @NotNull(message = TribeParamError.NOT_NULL)
    private String lastName;
    @NotNull(message = TribeParamError.NOT_NULL)
    private String formattedName;
    private String dateOfBirth;
    private String gender;
    private String country;
}
