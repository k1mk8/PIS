package com.pisproject.lawtextdb.unit.model;

import com.pisproject.lawtextdb.model.mongo.LawText;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModelMongoLawTextTests {
    @Test
    void testLawTextNameID(){
        LawText lawText = new LawText(1, "test");
        assertEquals(1, lawText.getId());
        assertEquals("test", lawText.getName());
    }

    @Test
    void testLawTextFile(){
        MockMultipartFile file = new MockMultipartFile("test", new byte[1]);
        try {
            LawText lawText = new LawText(file);
            assertFalse(lawText.isAccepted());
        }
        catch(IOException e){
            fail();
        }
    }

    @Test
    void testUpdateReferences() throws IOException {
        MockMultipartFile file = new MockMultipartFile("test", new byte[1]);
        LawText lawText = new LawText(file);
        lawText.updateReferences(2, "lawText2");
        Map<Integer, String> result = lawText.getReferences();
        Map<Integer, String> expected = new HashMap<>();
        expected.put(2, "lawText2");
        assertEquals(expected, result);
    }
}
