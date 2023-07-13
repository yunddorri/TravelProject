package com.example.travel.config;

import com.example.travel.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable{
    private static final long serialVersionUID = 178630l;

    private String name, email, picture;
    private Long id;
    private String role;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.id = user.getId();
        this.role = user.getRole().getKey().replace("ROLE_", "");
    }
}
