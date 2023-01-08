package com.pisproject.lawtextdb.unit.controller;

import com.pisproject.lawtextdb.controller.UserController;
import com.pisproject.lawtextdb.controller.implementation.UserControllerImpl;
import com.pisproject.lawtextdb.service.implementation.UserAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTests {
    UserControllerImpl controller;
    UserAuthServiceImpl service;

    @BeforeEach
    void initializeTests(){
        service = mock(UserAuthServiceImpl.class);
        controller = new UserControllerImpl();
        ReflectionTestUtils.setField(controller, "userAuthService", service);
    }

    @Test
    void testCreateUser(){
        when(service.createUser("username", "password")).thenReturn("Successfully created user");
        String result = controller.createUser(new UserController.LoginRequest("username", "password"));
        assertEquals("Successfully created user", result);
    }

    @Test
    void testGetLoginToken() throws NoSuchAlgorithmException {
        when(service.addToken("username", "password")).thenReturn("token");
        String result = controller.getLoginToken(new UserController.LoginRequest("username", "password"));
        assertEquals("token", result);
    }

    @Test
    void testDeleteLoginToken(){
        UserAuthServiceImpl mockService = mock(UserAuthServiceImpl.class);
        ReflectionTestUtils.setField(controller, "userAuthService", mockService);
        controller.deleteLoginToken(new UserController.AuthRequest("username", "token"));
        assertNotEquals(new Object(), mockService);
    }
}
