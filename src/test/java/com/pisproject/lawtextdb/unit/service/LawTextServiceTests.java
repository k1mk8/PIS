package com.pisproject.lawtextdb.unit.service;

import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.repository.mongo.LawTextRepository;
import com.pisproject.lawtextdb.repository.solr.SolrLawTextRepository;
import com.pisproject.lawtextdb.service.PrimarySequenceService;
import com.pisproject.lawtextdb.service.implementation.LawTextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LawTextServiceTests {
    private LawTextServiceImpl service;

    @BeforeEach
    void setUpService(){
        LawTextRepository lawTextRepository = Mockito.mock(LawTextRepository.class);
        SolrLawTextRepository solrLawTextRepository = Mockito.mock(SolrLawTextRepository.class);
        PrimarySequenceService primarySequenceService = Mockito.mock(PrimarySequenceService.class);
        service = new LawTextServiceImpl();
        ReflectionTestUtils.setField(service, "lawTextRepository", lawTextRepository);
        ReflectionTestUtils.setField(service, "solrLawTextRepository", solrLawTextRepository);
        ReflectionTestUtils.setField(service, "primarySequenceService", primarySequenceService);
    }

    @Test
    void testGetAll(){
        List<LawText> result = service.getAll();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testGetLawTextById(){
        Optional<LawText> result = service.getLawTextById(1);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetLawTextByName(){
        List<Optional<LawText>> result = service.getLawTextByName("test");
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testGetLawTextByRawText(){
        List<Optional<LawText>> result = service.getLawTextByRawText("testText");
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testAddLawTextToSolr(){
        LawText lawText = new LawText(1, "test");
        service.addLawTextToSolr(lawText, "testText");
    }

    @Test
    void testAddLawText(){
        LawText result = service.addLawText(new LawText());
        assertNull(result);
    }

    @Test
    void testAddLawTextFile(){
        MockMultipartFile file = new MockMultipartFile("test", "test.pdf", "pdf", new byte[1]);
        LawText result = service.addLawText(file);
        assertEquals(new LawText(), result);
    }

    @Test
    void testDeleteAllLawTexts(){
        service.deleteAllLawTexts();
    }
}
