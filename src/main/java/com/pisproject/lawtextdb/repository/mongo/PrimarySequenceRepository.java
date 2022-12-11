package com.pisproject.lawtextdb.repository.mongo;

import com.pisproject.lawtextdb.model.mongo.PrimarySequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimarySequenceRepository extends MongoRepository<PrimarySequence, String> {
}
