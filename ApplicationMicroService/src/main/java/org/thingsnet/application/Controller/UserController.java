package org.thingsnet.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thingsnet.application.DTO.AuthDTO;
import org.thingsnet.application.Data.User;
import org.thingsnet.application.Services.DeviceService;
import org.thingsnet.application.Services.UserService;
import org.thingsnet.application.util.ThignsNetException.EmailExistException;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ErrorResponse;
import org.thingsnet.application.util.ThignsNetException.ThingsNetException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DeviceService deviceService;


    //    @PreAuthorize("isAuthenticated()")
    @GetMapping("/devices/{userId}")
    public ResponseEntity<?> getDevices(@PathVariable(name = "userId") String userId) {
        try {
            return ResponseEntity.ok(deviceService.findDeviceByUserId(UUID.fromString(userId)));
        } catch (ThingsNetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") String userId) {
        try {
            userService.deleteUser(UUID.fromString(userId));
            return ResponseEntity.ok("Deleted user successfully");
        } catch (ThingsNetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
