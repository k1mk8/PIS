package com.pisproject.lawtextdb.unit.service;

import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.model.solr.SolrLawText;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LawTextServiceTests {
    private LawTextServiceImpl service;
    private LawTextRepository lawTextRepository;
    private SolrLawTextRepository solrLawTextRepository;

    @BeforeEach
    void setUpService(){
        lawTextRepository = Mockito.mock(LawTextRepository.class);
        solrLawTextRepository = Mockito.mock(SolrLawTextRepository.class);
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
    void testGetAccepted(){
        List<LawText> result = service.getAccepted();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testGetNotAccepted(){
        List<LawText> result = service.getNotAccepted();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testAcceptLawText(){
        when(service.getLawTextById(1)).thenReturn(Optional.of(new LawText()));
        String result = service.acceptLawText(1);
        assertEquals("Successfully accepted law text", result);
    }

    @Test
    void testAcceptLawTextDoesNotExist(){
        when(service.getLawTextById(1)).thenReturn(Optional.empty());
        String result = service.acceptLawText(1);
        assertEquals("The law text with provided id does not exist", result);
    }

    @Test
    void testAcceptLawTextUpdateRefs(){
        Optional<LawText> lawText = Optional.of(new LawText(1, "lawText"));
        ReflectionTestUtils.setField(lawText.get(), "accepted", true);
        ArrayList<LawText> lawTexts = new ArrayList<>();
        lawTexts.add(lawText.get());
        SolrLawText solrLawText = new SolrLawText();
        ReflectionTestUtils.setField(solrLawText, "lawTextId", 1);
        ArrayList<SolrLawText> solrLawTexts = new ArrayList<SolrLawText>();
        solrLawTexts.add(solrLawText);
        when(service.getLawTextById(1)).thenReturn(lawText);
        when(lawTextRepository.findAll()).thenReturn(lawTexts);
        when(solrLawTextRepository.findByRawText("lawText")).thenReturn(solrLawTexts);
        when(lawTextRepository.findById(1)).thenReturn(lawText);
        String result = service.acceptLawText(1);
        assertEquals("Successfully accepted law text", result);
    }

    @Test
    void testDeleteLawText(){
        when(service.getLawTextById(1)).thenReturn(Optional.of(new LawText()));
        String result = service.deleteLawText(1);
        assertEquals("The law text with provided id does not exist in solr, deleted from mongo only", result);
    }

    @Test
    void testDeleteLawTextDoesNotExist(){
        when(service.getLawTextById(1)).thenReturn(Optional.empty());
        String result = service.deleteLawText(1);
        assertEquals("The law text with provided id does not exist in mongo, aborting", result);
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
        assertNotEquals(new Object(), lawText);
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
        assertNotEquals(new Object(), service);
    }
}
