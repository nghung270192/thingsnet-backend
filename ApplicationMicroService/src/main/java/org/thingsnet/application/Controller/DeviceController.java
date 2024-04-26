package org.thingsnet.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thingsnet.application.Data.Device;
import org.thingsnet.application.Services.DeviceService;
import org.thingsnet.application.util.ThignsNetException.DeviceNameExistException;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ErrorResponse;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;
import org.thingsnet.application.util.ThignsNetException.UserNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

//    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> saveDevice(@RequestBody Device device) {

        try {
            return new ResponseEntity<>(deviceService.saveDevice(device), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .errorCode(e.getErrorCode())
                    .build(), HttpStatus.NOT_FOUND);
        } catch (DeviceNameExistException e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .message(e.getMessage())
                    .errorCode(e.getErrorCode())
                    .build(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .errorCode(ThingsNetErrorCode.BAD_REQUEST_PARAMS)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    //    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable(name = "deviceId") String deviceId) {
        try {
            deviceService.deleteDevice(UUID.fromString(deviceId));
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .errorCode(ThingsNetErrorCode.BAD_REQUEST_PARAMS)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }


}
