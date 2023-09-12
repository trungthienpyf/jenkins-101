package org.tribe_sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class HolderUpdateParams extends BaseParam {
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String holderId;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String businessName;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String firstName;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String lastName;
    @JsonProperty("address_line_1")
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String addressLine1;
    @JsonProperty("address_line_2")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String addressLine2;
    @JsonProperty("address_line_3")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String addressLine3;
    @JsonProperty("address_line_4")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String addressLine4;
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String countryIson;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 9, message = TribeParamError.MAX_LENGTH)
    private String zipCode;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String city;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String state;
    @NotNull(message = TribeParamError.NOT_NULL)

    @Size(min = 6, message = TribeParamError.MIN_LENGTH)
    @Size(max = 15, message = TribeParamError.MAX_LENGTH)
    private String phoneNumber;
    @Size(min = 6, message = TribeParamError.MIN_LENGTH)
    @Size(max = 1000, message = TribeParamError.MAX_LENGTH)
    @Pattern(regexp = TribeParamError.REGEX_EMAIL, message = TribeParamError.PATTERN_EMAIL)
    private String email;
    @Size(min = 10, message = TribeParamError.MIN_LENGTH)
    @Size(max = 10, message = TribeParamError.MAX_LENGTH)
    @Pattern(regexp = TribeParamError.REGEX_DATE, message = TribeParamError.PATTERN_DATE)
    private String dateOfBirth;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String holderLimitGroupId;

}
