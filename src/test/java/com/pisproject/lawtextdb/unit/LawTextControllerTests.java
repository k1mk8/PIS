package com.pisproject.lawtextdb.unit;

import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.service.LawTextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pisproject.lawtextdb.controller.implementation.LawTextControllerImpl;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LawTextControllerTests {
    LawTextControllerImpl controller;
    LawTextService service;
    LawText testLawText;

    @BeforeEach
    void initializeTests(){
        service = mock(LawTextService.class);
        controller = new LawTextControllerImpl();
        controller.setLawTextService(service);
        testLawText = new LawText();
    }

    @Test
    void testHello(){
        String result = controller.hello();
        assert result.equals("Hello");
    }

    @Test
    void testGetAll(){
        ArrayList<LawText> expectedList = new ArrayList<LawText>(){{add(testLawText);}};
        when(service.getAll()).thenReturn(new ArrayList<LawText>(){{add(testLawText);}});
        List<LawText> result = controller.getAll();
        assert result.equals(expectedList);
    }

    @Test
    void testGetLawTextById(){
        when(service.getLawTextById(1)).thenReturn(Optional.of(new LawText()));
        Optional<LawText> result = controller.getLawTextById(1);
        assert result.equals(Optional.of(testLawText));
    }

    @Test
    void testGetLawTextByIdNotFound(){
        when(service.getLawTextById(1)).thenReturn(Optional.empty());
        Optional<LawText> result = controller.getLawTextById(1);
        assert result.isEmpty();
    }

    @Test
    void testGetLawTextByName(){
        ArrayList<Optional<LawText>> expectedList = new ArrayList<Optional<LawText>>(){{add(Optional.of(testLawText));}};
        when(service.getLawTextByName("test")).thenReturn(
                new ArrayList<Optional<LawText>>(){{add(Optional.of(testLawText));}}
        );
        ArrayList<Optional<LawText>> result = controller.getLawTextByName("test");
        assert result.equals(expectedList);
    }

    @Test
    void testGetLawTextByNameNotFound(){
        when(service.getLawTextByName("test")).thenReturn(new ArrayList<Optional<LawText>>(){{add(Optional.empty());}});
        ArrayList<Optional<LawText>> result = controller.getLawTextByName("test");
        assert result.get(0).isEmpty();
    }

    @Test
    void testGetLawTextByRawText(){
        ArrayList<Optional<LawText>> expectedList = new ArrayList<Optional<LawText>>(){{add(Optional.of(testLawText));}};
        when(service.getLawTextByRawText("aaaaaaaaaa")).thenReturn(
                new ArrayList<Optional<LawText>>(){{add(Optional.of(testLawText));}}
        );
        ArrayList<Optional<LawText>> result = controller.getLawTextByRawText("aaaaaaaaaa");
        assert result.equals(expectedList);
    }

    @Test
    void testGetLawTextByRawTextNotFound(){
        when(service.getLawTextByName("aaaaaaaaaa")).thenReturn(
                new ArrayList<Optional<LawText>>(){{add(Optional.empty());}}
        );
        ArrayList<Optional<LawText>> result = controller.getLawTextByName("aaaaaaaaaa");
        assert result.get(0).isEmpty();
    }

    @Test
    void testAddLawText(){
        when(service.addLawText(testLawText)).thenReturn(testLawText);
        LawText result = controller.addLawText(testLawText);
        assert result.equals(testLawText);
    }

    @Test
    void testAddLawTextFile(){
        MockMultipartFile file = new MockMultipartFile("test", new byte[1]);
        when(service.addLawText(file)).thenReturn(testLawText);
        LawText result = controller.addLawText(file);
        assert result.equals(testLawText);
    }
}
