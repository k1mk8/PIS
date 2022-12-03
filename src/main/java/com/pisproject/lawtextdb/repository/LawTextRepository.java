package com.pisproject.lawtextdb.repository;

import com.pisproject.lawtextdb.model.LawText;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LawTextRepository extends MongoRepository<LawText, Integer> {
}
