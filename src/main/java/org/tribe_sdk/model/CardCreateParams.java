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
import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CardCreateParams extends BaseParam implements Serializable {

    private List<String> accountId;
    private List<String> accountOwnerId;
    private String holderId;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 64, message = TribeParamError.MAX_LENGTH)
    private String cardProgramId;
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String cardCountryIson;
    @NotNull(message = TribeParamError.NOT_NULL)
    private int cardVirtual;
    @JsonProperty("card_name_line_3")
    @NotNull(message = TribeParamError.NOT_NULL)
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 26, message = TribeParamError.MAX_LENGTH)
    private String cardNameLine3;
    @JsonProperty("card_name_line_4")
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String cardNameLine4;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String deliveryFirstName;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String deliveryLastName;
    @JsonProperty("delivery_address_line_1")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String deliveryAddressLine1;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String deliveryCity;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String deliveryState;
    private int orderShippingMethod;
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String deliveryCountryIson;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 9, message = TribeParamError.MAX_LENGTH)
    private String deliveryZipcode;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String holderBusinessName;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String holderFirstName;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String holderLastName;
    @JsonProperty("holder_address_line_1")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String holderAddressLine1;
    @JsonProperty("holder_address_line_2")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String holderAddressLine2;
    @JsonProperty("holder_address_line_3")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String holderAddressLine3;
    @JsonProperty("holder_address_line_4")
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    private String holderAddressLine4;
    @Size(min = 3, message = TribeParamError.MIN_LENGTH)
    @Size(max = 3, message = TribeParamError.MAX_LENGTH)
    private String holderCountryIson;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 9, message = TribeParamError.MAX_LENGTH)
    private String holderZipcode;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String holderCity;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 50, message = TribeParamError.MAX_LENGTH)
    private String holderState;
    @Size(min = 6, message = TribeParamError.MIN_LENGTH)
    @Size(max = 15, message = TribeParamError.MAX_LENGTH)
    private String holderPhoneNumber;
    @Size(min = 6, message = TribeParamError.MIN_LENGTH)
    @Size(max = 100, message = TribeParamError.MAX_LENGTH)
    @Pattern(regexp = TribeParamError.REGEX_EMAIL, message = TribeParamError.PATTERN_EMAIL)
    private String holderEmail;
    @Size(min = 10, message = TribeParamError.MIN_LENGTH)
    @Size(max = 10, message = TribeParamError.MAX_LENGTH)
    @Pattern(regexp = TribeParamError.REGEX_DATE, message = TribeParamError.PATTERN_DATE)
    private String holderDateOfBirth;
    private String cardReferenceId;

}
