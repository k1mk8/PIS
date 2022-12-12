package com.pisproject.lawtextdb.model.solr;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@SolrDocument(collection = "pis")
public class SolrLawText {

    @Id
    private String id;

    @Indexed(type = "text_en")
    private String name;

    @Indexed(type = "text_en")
    private String rawText;

    @Indexed(type = "id")
    private int lawTextId;
}
