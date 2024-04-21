package org.thingsnet.application.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thingsnet.application.Data.User;
import org.thingsnet.application.Entities.RoleEntity;
import org.thingsnet.application.Entities.UserEntity;
import org.thingsnet.application.Repositories.RoleRepository;
import org.thingsnet.application.Repositories.UserRepository;
import org.thingsnet.application.util.ThignsNetException.EmailExistException;
import org.thingsnet.application.util.ThignsNetException.ThingsNetErrorCode;
import org.thingsnet.application.util.ThignsNetException.ThingsNetException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    public User createUser(String email, String password, String role) throws ThingsNetException {
        RoleEntity adminRole = roleRepository.findByName(role);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(password));
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(adminRole);
        userEntity.setRoles(roleEntities);
        userEntity.setId(UUID.randomUUID());
        userEntity.setAdditionalInfo("{}");
        boolean hasExistedEmail = userRepository.existsByEmail(email);
        if (hasExistedEmail) {
            throw new EmailExistException("Email already exists", ThingsNetErrorCode.EMAIL_EXIST);
        }
        UserEntity userSaved = userRepository.save(userEntity);
        return userSaved.toData();
    }
}
