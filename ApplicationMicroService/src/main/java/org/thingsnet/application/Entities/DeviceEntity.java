package org.thingsnet.application.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsnet.application.Data.Device;
import org.thingsnet.application.Data.id.DeviceId;

import java.util.UUID;

@Data
@Entity
@Table(name = "devices")
public class DeviceEntity implements ToData<Device> {
    @Id
    @Column(name = ModelConstants.ID_PROPERTY, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "created_time")
    private long createdTime;

    @Column(name = "name")
    private String name;


    @Column(name = "updated_time")
    private long updatedTime;

    @Column(name = "attributes")
    private String attributes;

    @Column(name = "profile")
    private String profile;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "status")
    private boolean status;

    @Override
    public Device toData() {
        Device device = new Device();
        device.setId(new DeviceId(id));
        device.setName(name);
        device.setAttributes(attributes);
        device.setStatus(status);
        device.setProfile(profile);
        device.setUserId(userId);
        device.setUpdatedTime(updatedTime);
        device.setCreatedTime(createdTime);
        return device;
    }
}