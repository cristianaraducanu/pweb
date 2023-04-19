package com.foodiefrenzy.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;

    private String lastName;

    private String username;

    private String mail;

    private String password;

    private String role;
}