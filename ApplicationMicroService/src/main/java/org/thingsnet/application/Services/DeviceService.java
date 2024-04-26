package org.thingsnet.application.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thingsnet.application.Data.Device;
import org.thingsnet.application.Entities.DeviceEntity;
import org.thingsnet.application.Repositories.DeviceRepository;
import org.thingsnet.application.Repositories.RoleRepository;
import org.thingsnet.application.Repositories.UserRepository;
import org.thingsnet.application.util.ThignsNetException.DeviceNameExistException;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;
import org.thingsnet.application.util.ThignsNetException.ThingsNetException;
import org.thingsnet.application.util.ThignsNetException.UserExistException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public Device saveDevice(Device device) throws ThingsNetException {

        DeviceEntity deviceEntity = new DeviceEntity();

        if (device.getId() != null) {
            deviceEntity.setId(device.getId().getId());
            deviceEntity.setCreatedTime(device.getCreatedTime());
        } else {
            deviceEntity.setId(UUID.randomUUID());
            deviceEntity.setCreatedTime(System.currentTimeMillis());
        }
        deviceEntity.setUpdatedTime(System.currentTimeMillis());
        deviceEntity.setName(device.getName());
        deviceEntity.setProfile(device.getProfile());
        deviceEntity.setStatus(device.isStatus());
        deviceEntity.setAttributes(device.getAttributes());
        deviceEntity.setUserId(device.getUserId());

        /*check the existence of the user id*/
        if (!userRepository.existsById(device.getUserId())) {
            throw new UserExistException("User not found", ThingsNetErrorCode.USER_NOT_FOUND);
        }

        /*Check the existence of the device name.*/
        if (deviceRepository.existsByName(device.getName())) {
            throw new DeviceNameExistException("Device name already exist", ThingsNetErrorCode.DEVICE_NAME_ALREADY_EXIST);
        }
        return deviceRepository.save(deviceEntity).toData();
    }

    public void deleteDevice(UUID deviceId) throws ThingsNetException {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(deviceId);
        if (deviceEntityOptional.isPresent()) {
            deviceRepository.deleteById(deviceId);
        } else {
            throw new ThingsNetException("Device not found", ThingsNetErrorCode.DEVICE_NOT_FOUND);
        }
    }

    public Device findDeviceById(UUID deviceId) throws ThingsNetException {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(deviceId);
        if (deviceEntityOptional.isPresent()) {
            DeviceEntity deviceEntity = deviceEntityOptional.get();
            return deviceEntity.toData();
        } else {
            throw new ThingsNetException("Device not found", ThingsNetErrorCode.DEVICE_NOT_FOUND);
        }

    }

    public Device[] findDeviceByUserId(UUID userId) throws ThingsNetException {
        Optional<ArrayList<DeviceEntity>> deviceEntityOptional = deviceRepository.findByUserId(userId);
        if (deviceEntityOptional.isPresent()) {
            ArrayList<DeviceEntity> deviceEntities = deviceEntityOptional.get();
            return deviceEntities.stream().map(DeviceEntity::toData).toArray(Device[]::new);
        } else {
            throw new ThingsNetException("Device not found", ThingsNetErrorCode.DEVICE_NOT_FOUND);
        }

    }

}
