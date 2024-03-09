package com.gogoasa.c.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "app_user")
public class User {
    @Id
    private String username;
    private String email;
    private String password;
    private String roles = "ROLE_USER";
}
