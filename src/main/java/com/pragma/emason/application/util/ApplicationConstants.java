package com.pragma.emason.application.util;

public class ApplicationConstants {


    private ApplicationConstants() {

    }



    //JWT HANDLER
    public static final String SECRET_KEY_PASSWORD = "mi_clave_secreta_fija_que_debe_ser_larga_123456";

    // UserAccountRequestDTO validation messages
    public static final String NAME_REQUIRED = "Name is required";
    public static final String NAME_NOT_BLANK = "Name cannot be blank";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String LAST_NAME_NOT_BLANK = "Last name cannot be blank";
    public static final String IDENTITY_DOCUMENT_REQUIRED = "Identity document is required";
    public static final String PHONE_SIZE = "Phone number must be between 7 and 13 digits";
    public static final String BIRTH_DATE_REQUIRED = "Birth date is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_NOT_BLANK = "Email cannot be blank";
    public static final String EMAIL_VALID = "Email should be valid";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String ROLE_ID_REQUIRED = "Role is required";

    // JWT Handler
    public static final Integer TOKEN_EXPIRATION_TIME = 180000;

}
