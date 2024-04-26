package org.thingsnet.application.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thingsnet.application.DTO.AuthDTO;
import org.thingsnet.application.Data.User;
import org.thingsnet.application.Services.AuthService;
import org.thingsnet.application.Services.UserService;
import org.thingsnet.application.util.ThignsNetException.EmailExistException;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ErrorResponse;
import org.thingsnet.application.util.ThignsNetException.ErrorResponse.ThingsNetErrorCode;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequest userLogin) throws IllegalAccessException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Token requested for user :{}", authentication.getAuthorities());
            String token = authService.generateToken(authentication);

            AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.UNAUTHORIZED.value()).errorCode(ThingsNetErrorCode.AUTHENTICATION).message("Authentication failed").timestamp(System.currentTimeMillis()).build();

            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO.Register userRegister) {
        try {
            User user = userService.createUser(userRegister.email(), userRegister.password(), userRegister.role());
            return ResponseEntity.ok(user);
        } catch (EmailExistException e) {
            ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.CONFLICT.value()).timestamp(System.currentTimeMillis()).errorCode(e.getErrorCode()).build();
            System.out.println(errorResponse);
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }

}