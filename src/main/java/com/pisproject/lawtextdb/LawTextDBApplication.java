package com.pisproject.lawtextdb;

import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@RestController
public class LawTextDBApplication {

	@RequestMapping("/")
	public String home() {
		return "API Test - rzeczy działają";
	}

	@RequestMapping("/db")
	public String readDataFromMongoDB() {
		String uri = "mongodb://mongodb:27017";
		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase db = mongoClient.getDatabase("test");
			MongoCollection<Document> coll = db.getCollection("lawText");
			MongoCursor<Document> cursor = coll.find().iterator();
			try {
				Document databaseDoc = cursor.next();
				while(cursor.hasNext()){
					databaseDoc = cursor.next();
				}
				return databaseDoc.toString();
			} finally {
				cursor.close();
			}
		}
	}

	@RequestMapping("/db/add")
	public String saveDataToMongoDB() {
		String uri = "mongodb://mongodb:27017";
		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase db = mongoClient.getDatabase("test");
			MongoCollection<Document> coll = db.getCollection("lawText");
			Document doc1 = new Document("name", "LawText2").append("description", "example");
			InsertOneResult result = coll.insertOne(doc1);
		}
		return "Dodano wiersz";
	}

	public static void main(String[] args) {
		SpringApplication.run(LawTextDBApplication.class, args);
	}

}
