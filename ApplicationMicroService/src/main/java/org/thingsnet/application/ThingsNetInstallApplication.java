package org.thingsnet.application;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thingsnet.application.Entities.RoleEntity;
import org.thingsnet.application.Entities.UserEntity;
import org.thingsnet.application.Repositories.RoleRepository;
import org.thingsnet.application.Repositories.UserRepository;

import java.util.*;

@Component
public class ThingsNetInstallApplication implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;

        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");

        RoleEntity adminRole = roleRepository.findByName("USER");
        UserEntity user = new UserEntity();
        user.setEmail("nguyen");
        user.setPassword(passwordEncoder.encode("test"));
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(adminRole);
        user.setRoles(roleEntities);
        user.setId(UUID.randomUUID());

        user.setAdditionalInfo("{}");
        userRepository.save(user);

        System.out.println(user);
        alreadySetup = true;
    }

    @Transactional
    RoleEntity createRoleIfNotFound(String name) {

        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity();
            role.setName(name);
            role.setId(UUID.randomUUID());
            roleRepository.save(role);
        }
        return role;
    }
}