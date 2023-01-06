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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        String pass = "password";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String strHash = encoder.encode(pass);

        return strings -> {
            repository.save(new User(1, "admin", strHash));
            repository.save(new User(2, "admin2", strHash));
            repository.save(new User(3, "admin3", strHash));
        };
    }
}