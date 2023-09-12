package org.tribe_sdk.validator;

import org.tribe_sdk.exception.TribeException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class TribeValidator {

    public static <T> void validationPrams(T params) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolationsInvalidUser = validator.validate(params);

        if (constraintViolationsInvalidUser.size() > 0) {
            for (ConstraintViolation<T> constraintViolation : constraintViolationsInvalidUser) {
                throw new TribeException(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
            }
        }
    }
}
