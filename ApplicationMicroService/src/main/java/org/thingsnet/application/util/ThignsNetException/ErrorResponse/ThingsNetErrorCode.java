package org.thingsnet.application.util.ThignsNetException.ErrorResponse;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ThingsNetErrorCode {

    GENERAL(2),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    CREDENTIALS_EXPIRED(15),
    PERMISSION_DENIED(20),
    INVALID_ARGUMENTS(30),
    BAD_REQUEST_PARAMS(31),
    ITEM_NOT_FOUND(32),
    TOO_MANY_REQUESTS(33),
    TOO_MANY_UPDATES(34),
    SUBSCRIPTION_VIOLATION(40),
    EMAIL_ALREADY_EXIST(60),
    USER_NOT_FOUND(61),
    DEVICE_NAME_ALREADY_EXIST(70),
    DEVICE_NAME_NOT_FOUND(71),
    DEVICE_NOT_FOUND(72);

    private final int errorCode;

    ThingsNetErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }

}
