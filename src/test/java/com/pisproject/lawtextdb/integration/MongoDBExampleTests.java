package com.pisproject.lawtextdb.integration;

import com.mongodb.client.*;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoDBExampleTests {
    @Test
    void readDataFromMongoDB() {
        String expectedName = "LawText1";
        String expectedDesc = "example";
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("test");
            MongoCollection<Document> coll = db.getCollection("lawText");
            MongoCursor<Document> cursor = coll.find().iterator();
            try {
                while (cursor.hasNext()) {
                    Document databaseDoc = cursor.next();
                    assertEquals(databaseDoc.getString("name"), expectedName);
                    assertEquals(databaseDoc.getString("description"), expectedDesc);
                }
            } finally {
                cursor.close();
            }
        }
    }
    @Test
    void readDataFromMongoDB2() {
        String expectedName = "LawText2";
        String expectedDesc = "example";
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("test");
            MongoCollection<Document> coll = db.getCollection("lawText");
            MongoCursor<Document> cursor = coll.find().iterator();
            try {
                while (cursor.hasNext()) {
                    Document databaseDoc = cursor.next();
                    assertEquals(databaseDoc.getString("name"), expectedName);
                    assertEquals(databaseDoc.getString("description"), expectedDesc);
                }
            } finally {
                cursor.close();
            }
        }
    }
}