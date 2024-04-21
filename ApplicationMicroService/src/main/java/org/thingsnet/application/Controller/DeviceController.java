package org.thingsnet.application.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    @PreAuthorize("hasAnyRole('SCOPE_ADMIN','SCOPE_USER')")
    @GetMapping
    public String getDevices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve the roles (authorities) associated with the user
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                // Do something with the role
                System.out.println("Role: " + role);
            }
        }
        System.out.println(authentication);

        return "I am a getDevices";
    }

}
