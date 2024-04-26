package org.thingsnet.application.util.ThignsNetException.ErrorResponse;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    protected int status;
    protected String message;
    protected ThingsNetErrorCode errorCode;
    protected long timestamp;
}
