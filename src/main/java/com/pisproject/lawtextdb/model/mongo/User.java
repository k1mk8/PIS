package com.pisproject.lawtextdb.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String token;

    public User(){};

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}

