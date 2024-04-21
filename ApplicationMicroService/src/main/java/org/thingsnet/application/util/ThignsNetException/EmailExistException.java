package org.thingsnet.application.util.ThignsNetException;

public class EmailExistException extends ThingsNetException {
    public EmailExistException(String message, ThingsNetErrorCode errorCode) {
        super(message, errorCode);
    }
}
