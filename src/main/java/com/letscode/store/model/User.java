package com.letscode.store.model;


import com.letscode.store.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    public static User convert(UserDTO dto) {
        User user = new User();
        user.setEnabled(dto.getEnabled());
        user.setPassword(dto.getPassword());
        user.setUserName(dto.getUserName());
        return user;
    }
}
