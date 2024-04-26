package org.thingsnet.application.util.ThignsNetException;

import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

public class UserExistException extends ThingsNetException {
    public UserExistException(String message, ThingsNetErrorCode errorCode) {
        super(message, errorCode);
    }
}
