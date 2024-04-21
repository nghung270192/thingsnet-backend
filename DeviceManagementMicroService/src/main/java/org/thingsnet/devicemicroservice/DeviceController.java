package org.thingsnet.devicemicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
    @Autowired
    DevicceService devicceService;

    @PostMapping
    public void createDevice(@RequestBody Device device) {
        devicceService.createDevice(device);
        log.info("new device is created {}", device);
    }
    @GetMapping
    public String getDevice() {
        return "Hello. I am device micro service";
    }

}
