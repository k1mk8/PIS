package com.pisproject.lawtextdb.unit.model;

import com.pisproject.lawtextdb.model.mongo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelMongoUserTests {

    @Test
    void testUserWithoutId(){
        User user = new User("username", "password");
        assertEquals("username", user.getUsername());
    }

    @Test
    void testUserWithId(){
        User user = new User(1,"username", "password");
        assertEquals(1, user.getId());
    }
}
