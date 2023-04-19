package com.foodiefrenzy.dto;

import com.foodiefrenzy.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserDTO {
    private Long id;

    private String password;

    private String username;

    private String mail;

    private String role;
}
