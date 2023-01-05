package com.pisproject.lawtextdb.config;

import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.model.mongo.User;
import com.pisproject.lawtextdb.repository.BaseRepository;
import com.pisproject.lawtextdb.repository.mongo.LawTextRepository;
import com.pisproject.lawtextdb.repository.mongo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@EnableMongoRepositories(basePackageClasses = BaseRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner lawTextCommandLineRunner(LawTextRepository repository) {
        return strings -> {
            repository.save(new LawText(1, "LawText1"));
            repository.save(new LawText(2, "LawText2"));
            repository.save(new LawText(3, "LawText3"));
        };
    }

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) throws NoSuchAlgorithmException {
        String pass = "password";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        String strHash = Base64Utils.encodeToString(hash);
        return strings -> {
            repository.save(new User(1, "admin", strHash));
            repository.save(new User(2, "admin2", strHash));
            repository.save(new User(3, "admin3", strHash));
        };
    }

}