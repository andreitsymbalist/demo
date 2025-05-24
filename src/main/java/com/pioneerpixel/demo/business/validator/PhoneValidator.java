package com.pioneerpixel.demo.business.validator;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String PHONE_REGEX = "^\\d{11}$";

    public static void validateFormat(String phone) {
        boolean isValid = Pattern.compile(PHONE_REGEX)
            .matcher(phone)
            .matches();
        if (!isValid) {
            throw new IllegalArgumentException("Incorrect phone format");
        }
    }
}
