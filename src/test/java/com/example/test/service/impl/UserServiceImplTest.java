package com.example.test.service.impl;

import com.example.test.model.User;
import com.example.test.repository.IUserRepository;
import com.example.test.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setEmail("ntorres12@icloud.com");
    }

    @Test
    void findAll() {
        when( userRepository.findAll()).thenReturn(Arrays.asList(user));
        assertNotNull(userRepository.findAll());
    }

    @Test
    void findById() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        assertNotNull(userRepository.findById(1));
    }

    @Test
    void save() {
        when( userRepository.save(any(User.class))).thenReturn(user);
        assertNotNull(userRepository.save(new User()));
    }

    @Test
    void deleteById() {
       // verify(userRepository).deleteById(any());
    }

    @Test
    void findByEmail() {
        when(userRepository.findByEmail(any())).thenReturn(user);
        assertNotNull(userRepository.findByEmail("ntorres12@icloud.com"));
    }
}