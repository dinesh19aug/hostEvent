package com.event.hostevent.util;

public enum EnumErrorCode {
    EMPTY_FIELDS("Please fill in all the fields");

    private String errorDesc;

    EnumErrorCode(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
