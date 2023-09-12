package org.tribe_sdk.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tribe_sdk.validator.TribeParamError;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@ToString
@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CardActivityParams extends BaseParam implements Serializable {

    @Size(min = 1, message = TribeParamError.MIN_LENGTH)
    @Size(max = 20, message = TribeParamError.MAX_LENGTH)
    private String cardId;
    @NotNull(message = TribeParamError.NOT_NULL)
    private Long dateFrom;
    @NotNull(message = TribeParamError.NOT_NULL)
    private Long dateTo;
    @Min(value = 1, message = TribeParamError.MIN_VALUE + "1")
    @Max(value = 100, message = TribeParamError.MAX_VALUE + "100")
    private int itemCount;

}
