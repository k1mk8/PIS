package com.pisproject.lawtextdb.service.implementation;

import com.pisproject.lawtextdb.model.mongo.User;
import com.pisproject.lawtextdb.repository.mongo.UserRepository;
import com.pisproject.lawtextdb.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(String username, String password) throws NoSuchAlgorithmException {
        if (userRepository.findByUsername(username).isPresent()){
            return "User with this username already exists";
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String passwordHash = Base64Utils.encodeToString(hash);
        User user = new User(username, passwordHash);
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public Optional<User> loadUserByToken(String username, String token) {
        return userRepository.findByUsernameAndToken(username, token);
    }

    @Override
    public String addToken(String username, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String passwordHash = Base64Utils.encodeToString(hash);
        Optional<User> user = userRepository.findByUsernameAndPassword(username, passwordHash);
        if (user.isEmpty()) {
            return "Invalid login and/or password";
        }
        else{
            String rawToken = UUID.randomUUID().toString();
            byte[] tokenHash = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            String token = Base64Utils.encodeToString(tokenHash);
            user.get().setToken(token);
            userRepository.save(user.get());
            return token;
        }
    }

    public void deleteToken(String username, String token){
        Optional<User> user = loadUserByToken(username, token);
        if (user.isPresent()){
            user.get().setToken(null);
            userRepository.save(user.get());
        }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

}
