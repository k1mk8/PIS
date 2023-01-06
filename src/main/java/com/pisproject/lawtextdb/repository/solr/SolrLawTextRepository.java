package com.pisproject.lawtextdb.repository.solr;

import com.pisproject.lawtextdb.model.solr.SolrLawText;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Optional;

public interface SolrLawTextRepository extends SolrCrudRepository<SolrLawText, String> {
    List<SolrLawText> findByName(String name);
    List<SolrLawText> findByRawText(String rawText);
    Optional<SolrLawText> findByLawTextId(int lawTextId);
}
