package org.thingsnet.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thingsnet.application.DTO.AuthDTO;
import org.thingsnet.application.Data.User;
import org.thingsnet.application.Services.UserService;
import org.thingsnet.application.util.ThignsNetException.EmailExistException;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO.Register userRegister) {
        try {
            User user = userService.createUser(userRegister.email(), userRegister.password(), userRegister.role());
            return ResponseEntity.ok(user);
        } catch (EmailExistException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .message(e.getMessage())
                    .status(HttpStatus.CONFLICT.value())
                    .timestamp(System.currentTimeMillis())
                    .errorCode(e.getErrorCode()).build();
            System.out.println(errorResponse);
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }
}
