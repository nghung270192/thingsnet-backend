package org.thingsnet.application.Data.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeviceId extends EntityId {
    public DeviceId(UUID uuid) {
        super(uuid);
        super.entityType = EntityType.DEVICE;
    }
}
