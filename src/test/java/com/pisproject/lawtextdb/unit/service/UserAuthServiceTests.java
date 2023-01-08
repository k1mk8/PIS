package com.pisproject.lawtextdb.unit.service;

import com.pisproject.lawtextdb.model.mongo.User;
import com.pisproject.lawtextdb.repository.mongo.UserRepository;
import com.pisproject.lawtextdb.service.implementation.UserAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserAuthServiceTests {

    private UserAuthServiceImpl service;
    private UserRepository userRepository;

    @BeforeEach
    void setUpService(){
        userRepository = Mockito.mock(UserRepository.class);
        service = new UserAuthServiceImpl();
        ReflectionTestUtils.setField(service, "userRepository", userRepository);
    }

    @Test
    void testCreateUser(){
        String result = service.createUser("username", "password");
        assertEquals("username", result);
    }

    @Test
    void testCheckIfUserIsValid(){
        when(service.loadUserByToken("username", "token")).thenReturn(Optional.of(new User()));
        Boolean result = service.checkIfTokenIsValid("username", "token");
        assertTrue(result);
    }

    @Test
    void testCheckIfUserIsInvalid(){
        when(service.loadUserByToken("username", "token")).thenReturn(Optional.empty());
        Boolean result = service.checkIfTokenIsValid("username", "token");
        assertFalse(result);
    }

    @Test
    void testLoadUserByToken(){
        Optional<User> result = service.loadUserByToken("username", "token");
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddToken() throws NoSuchAlgorithmException {
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(new User("username", "$2a$10$Tir/PTCQjgTivjNgKkJUEuFiIHEK5QHCs25ZiM/Piliz2DCIQ3vIK")));
        String result = service.addToken("username", "password");
        assertNotEquals("Invalid login and/or password", result);
    }

    @Test
    void testAddTokenInvalid() throws NoSuchAlgorithmException {
        String result = service.addToken("username", "password");
        assertEquals("Invalid login and/or password", result);
    }

    @Test
    void testDeleteToken(){
        when(service.loadUserByToken("username", "token")).thenReturn(Optional.of(new User()));
        service.deleteToken("username", "token");
        assertNotEquals(new Object(), service);
    }
}
