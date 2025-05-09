package com.example.Hospital.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error"),
    INVALID_KEY(101, "Uncategorized error"),
    USER_EXISTED(102, "User existed"),
    USER_NOT_EXISTED(105, "User not existed"),
    UNAUTHENTICATED(106, "Unauthenticated"),
    ;
    private int code;
    private String mess;

    ErrorCode(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }
    public int getCode() {
        return code;
    }

    public String getMess() {
        return mess;
    }

}
