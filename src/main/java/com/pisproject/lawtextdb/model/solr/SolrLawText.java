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

    @Indexed(type = "text_ik")
    private String name;

    @Indexed(type = "text_ik")
    private String rawText;

    private int lawTextId;
}
