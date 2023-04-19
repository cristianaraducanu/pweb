package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.UserDTO;
import com.foodiefrenzy.entity.User;
import com.foodiefrenzy.mapper.UserMapper;
import com.foodiefrenzy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void testCreateDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("abc");
        userDTO.setMail("abc@example.com");

        User user = new User();
        user.setId(1L);
        user.setUsername("abc");
        user.setMail("abc@example.com");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.createDTO(userDTO);

        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    void testGetAll() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("abc");
        user1.setMail("abc@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("abcd");
        user2.setMail("abcd@example.com");

        List<User> userList = List.of(user1, user2);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(1L);
        userDTO1.setUsername("abc");
        userDTO1.setMail("abc@example.com");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(2L);
        userDTO2.setUsername("abcd");
        userDTO2.setMail("abcd@example.com");

        List<UserDTO> userDTOList = List.of(userDTO1, userDTO2);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toDto(user1)).thenReturn(userDTO1);
        when(userMapper.toDto(user2)).thenReturn(userDTO2);

        List<UserDTO> result = userService.getAll();

        assertThat(result).isEqualTo(userDTOList);
    }
}
