package com.pisproject.lawtextdb.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lawText")
public class LawText {

    @Id
    private Integer id;
    private String name;
    private String description;

    public LawText() {
    }

    public LawText(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public LawText(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
