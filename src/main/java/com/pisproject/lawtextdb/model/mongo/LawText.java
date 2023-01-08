package com.pisproject.lawtextdb.model.mongo;


import lombok.Data;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "lawText")
public class LawText {

    @Id
    private Integer id;
    private String name;
    private LocalDateTime uploadDate;
    private boolean accepted;
    private Binary file;
    private Map<Integer, String> references;

    public LawText() {
    }

    public LawText(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public LawText(MultipartFile file) throws IOException {
        this.name = file.getOriginalFilename();
        this.uploadDate = LocalDateTime.now();
        this.accepted = false;
        this.file = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        this.references = new HashMap<>();
    }

    public void updateReferences(Integer id, String name) {
        this.references.put(id, name);
    }
}
