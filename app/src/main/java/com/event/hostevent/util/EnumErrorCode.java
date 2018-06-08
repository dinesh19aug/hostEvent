package com.event.hostevent.util;

public enum EnumErrorCode {
    EMPTY_FIELDS("Please fill in all the fields"),
    INVALID_EMAIL_PATTERN("Email Pattern is invalid"),
    INVALID_CREDENTIALS("Invalid Login. Either email or password is incorrect");


    private String errorDesc;

    EnumErrorCode(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
