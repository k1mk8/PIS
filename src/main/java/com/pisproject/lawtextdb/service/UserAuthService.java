package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.mongo.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UserAuthService {
    public String createUser(String username, String password);

    public Boolean checkIfTokenIsValid(String username, String token);
    public Optional<User> loadUserByToken(String username, String token);
    public String addToken(String username, String password) throws NoSuchAlgorithmException;
    public void deleteToken(String username, String token);
}