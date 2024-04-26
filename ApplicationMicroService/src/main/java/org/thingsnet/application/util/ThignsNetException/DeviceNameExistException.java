package org.thingsnet.application.util.ThignsNetException;

import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

public class DeviceNameExistException extends ThingsNetException {
    public DeviceNameExistException(String message, ThingsNetErrorCode errorCode) {
        super(message, errorCode);
    }
}
