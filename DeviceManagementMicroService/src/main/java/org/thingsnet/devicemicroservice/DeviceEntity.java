package org.thingsnet.devicemicroservice;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "devices")
public class DeviceEntity {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Device [id=" + id + ", name=" + name + "]";
    }
}
