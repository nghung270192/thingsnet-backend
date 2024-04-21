package org.thingsnet.application.util.ThignsNetException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private ThingsNetErrorCode errorCode;
    private long timestamp;
}
