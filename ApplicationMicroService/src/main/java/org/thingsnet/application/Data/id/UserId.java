package org.thingsnet.application.Data.id;

import java.util.UUID;

public class UserId extends EntityId {
    public UserId(UUID uuid) {
        super(uuid);
        super.entityType = EntityType.USER;
    }
}
