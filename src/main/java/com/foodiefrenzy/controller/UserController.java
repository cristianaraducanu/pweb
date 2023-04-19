package com.foodiefrenzy.controller;


import com.foodiefrenzy.dto.UserDTO;
import com.foodiefrenzy.enums.Role;
import com.foodiefrenzy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
@PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.createDTO(userDTO);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }


    @GetMapping("/roles")
    public List<String> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}
