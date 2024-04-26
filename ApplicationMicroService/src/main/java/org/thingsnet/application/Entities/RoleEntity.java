package org.thingsnet.application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = ModelConstants.ID_PROPERTY, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name")
    private String name;

}
