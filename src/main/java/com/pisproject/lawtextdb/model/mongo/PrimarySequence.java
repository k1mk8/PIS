package com.pisproject.lawtextdb.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "primarySequence")
public class PrimarySequence {

    @Id
    private String id;

    private Integer seq;

}
