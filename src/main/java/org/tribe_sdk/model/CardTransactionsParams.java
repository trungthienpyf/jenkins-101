package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CardTransactionsParams extends BaseParam {

    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String cardId;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String accountId;
    private Long dateFrom;
    private Long dateTo;
    @Min(value = 1, message = TribeParamError.MIN_VALUE + "1")
    @Max(value = 100, message = TribeParamError.MAX_VALUE + "100")
    private int itemCount;
    private Integer lastId;
    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String transactionId;
}
