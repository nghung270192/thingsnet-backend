package org.thingsnet.devicemicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DevicceService {
    public void createDevice(Device device) {
        log.info("new device is created {}", device);
    }
}
