package org.thingsnet.application.Data.id;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;


@Getter
public class EntityId extends UUIDBase {
    private EntityType entityType;
}
