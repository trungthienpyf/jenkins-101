package org.tribe_sdk.validator;


public interface TribeParamError {

    String REGEX_DATE = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    String REGEX_EMAIL = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
    String NOT_NULL = "not provided";
    String MIN_VALUE = "cannot be less than ";


    String MAX_VALUE = "cannot be more than ";
    String MAX_LENGTH = "is too long";
    String MIN_LENGTH = "is to short";
    String PATTERN_DATE = "must be yyyy-MM-dd format";
    String PATTERN_EMAIL = "is invalid";

}
