package com.pisproject.lawtextdb.repository.mongo;

import com.pisproject.lawtextdb.model.mongo.LawText;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LawTextRepository extends MongoRepository<LawText, Integer> {
}
