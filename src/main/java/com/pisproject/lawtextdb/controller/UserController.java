package com.pisproject.lawtextdb.controller;

import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;

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
    String getLoginToken(@RequestBody LoginRequest req) throws NoSuchAlgorithmException;
    public void deleteLoginToken(@RequestBody AuthRequest req);
}
