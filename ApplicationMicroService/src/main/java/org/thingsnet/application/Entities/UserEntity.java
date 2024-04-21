package org.thingsnet.application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thingsnet.application.Data.User;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseSqlEntity implements ToData<User> {


    @Column(name = "created_time")
    private long createdTime;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "enable")
    private boolean enable;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @Column(name = ModelConstants.ADDITIONAL_INFO_PROPERTY)
    private String additionalInfo;

    @Override
    public User toData() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAdditionalInfo(additionalInfo);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCreateTime(createdTime);
        return user;
    }
}
