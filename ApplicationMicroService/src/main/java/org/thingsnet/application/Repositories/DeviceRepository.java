package org.thingsnet.application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thingsnet.application.Entities.DeviceEntity;
import org.thingsnet.application.Entities.UserEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
    Optional<ArrayList<DeviceEntity>> findByUserId(UUID userId);

    Optional<DeviceEntity> findByName(String name);


    boolean existsByName(String email);
}