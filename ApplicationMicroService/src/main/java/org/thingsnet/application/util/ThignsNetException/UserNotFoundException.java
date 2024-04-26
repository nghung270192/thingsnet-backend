package org.thingsnet.application.util.ThignsNetException;

import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

public class UserNotFoundException extends ThingsNetException {
    public UserNotFoundException(String message, ThingsNetErrorCode errorCode) {
        super(message, errorCode);
    }
}
