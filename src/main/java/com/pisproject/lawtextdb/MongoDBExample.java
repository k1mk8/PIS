package com.pisproject.lawtextdb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
public class MongoDBExample {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("test");
            MongoCollection<Document> coll = db.getCollection("lawText");
            Document doc1 = new Document("name", "LawText1").append("description", "example");
            InsertOneResult result = coll.insertOne(doc1);
            System.out.println(result.getInsertedId());
        }
    }
}