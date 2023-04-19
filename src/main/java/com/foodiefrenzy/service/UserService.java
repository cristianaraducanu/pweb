package com.foodiefrenzy.service;


import com.foodiefrenzy.dto.UserDTO;
import com.foodiefrenzy.entity.User;
import com.foodiefrenzy.mapper.UserMapper;
import com.foodiefrenzy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDTO createDTO(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

}
