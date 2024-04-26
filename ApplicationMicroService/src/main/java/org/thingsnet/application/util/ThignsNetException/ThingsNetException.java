package org.thingsnet.application.util.ThignsNetException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThingsNetException extends Exception {
    private ThingsNetErrorCode errorCode;

    public ThingsNetException(String message, ThingsNetErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
