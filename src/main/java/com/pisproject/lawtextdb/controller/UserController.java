package com.pisproject.lawtextdb.controller;

import com.pisproject.lawtextdb.model.mongo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserController {

    public static class LoginRequest{
        public String username;
        public String password;

        public LoginRequest(String username, String password){
            this.username = username;
            this.password = password;
        }
    }

    public static class AuthRequest{
        public String username;
        public String token;

        public AuthRequest(String username, String token) {
            this.username = username;
            this.token = token;
        }
    }

    public String createUser(@RequestBody LoginRequest req) throws NoSuchAlgorithmException;
    public List<User> getUsers();
    String getLoginToken(@RequestBody LoginRequest req) throws NoSuchAlgorithmException;
    public void deleteLoginToken(@RequestBody AuthRequest req);
}
