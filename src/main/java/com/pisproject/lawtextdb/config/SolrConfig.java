package com.pisproject.lawtextdb.config;

import com.pisproject.lawtextdb.repository.solr.SolrLawTextRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableSolrRepositories(basePackageClasses = SolrLawTextRepository.class)
@Configuration
public class SolrConfig {
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://23.21.221.175:8983/solr").build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}


