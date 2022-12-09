package com.pisproject.lawtextdb.model;


import lombok.Data;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
@Document(collection = "lawText")
public class LawText {

    @Id
    private Integer id;
    private String name;
    private LocalDateTime uploadDate;
    private boolean accepted;
    private Binary file;
    private String rawText;

    public LawText() {
    }

    public LawText(Integer id, String name, String rawText) {
        this.id = id;
        this.name = name;
        this.rawText = rawText;
    }

    public LawText(MultipartFile file) throws IOException {
        this.name = file.getOriginalFilename();
        this.uploadDate = LocalDateTime.now();
        this.accepted = false;
        this.file = new Binary(BsonBinarySubType.BINARY, file.getBytes());
    }
}
