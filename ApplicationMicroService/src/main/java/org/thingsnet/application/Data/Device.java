package org.thingsnet.application.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thingsnet.application.Data.id.DeviceId;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device extends BaseData<DeviceId> {
    private String name;
    private long updatedTime;
    private String attributes;
    private String profile;
    private UUID userId;
    private boolean status;


}
