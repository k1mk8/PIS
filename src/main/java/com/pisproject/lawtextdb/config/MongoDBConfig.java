package com.pisproject.lawtextdb.config;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.repository.LawTextRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = LawTextRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(LawTextRepository repository) {
        return strings -> {
            repository.save(new LawText(1, "LawText1", "test"));
            repository.save(new LawText(2, "LawText2", "test"));
            repository.save(new LawText(3, "LawText3", "test"));
        };
    }

}