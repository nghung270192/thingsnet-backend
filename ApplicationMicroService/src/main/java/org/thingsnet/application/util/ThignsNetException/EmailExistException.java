package org.thingsnet.application.util.ThignsNetException;

import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

public class EmailExistException extends ThingsNetException {
    public EmailExistException(String message, ThingsNetErrorCode errorCode) {
        super(message, errorCode);
    }
}
