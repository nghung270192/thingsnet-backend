package org.thingsnet.application.Data.id;

public enum EntityType {

    DEVICE("DEVICE"), USER("USER"), ASSET("ASSET");

    private String entityType;

    EntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }
}
