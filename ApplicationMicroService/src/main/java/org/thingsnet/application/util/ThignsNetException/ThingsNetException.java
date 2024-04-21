package org.thingsnet.application.util.ThignsNetException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class ThingsNetException extends Exception {
    private ThingsNetErrorCode errorCode;

    ThingsNetException(String message, ThingsNetErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
