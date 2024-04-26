package org.thingsnet.application.Data.id;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntityId extends UUIDBase {
    protected EntityType entityType;

    public EntityId(UUID id) {
        super(id);
    }
}
