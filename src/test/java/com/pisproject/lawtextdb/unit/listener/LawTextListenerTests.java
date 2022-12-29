package com.pisproject.lawtextdb.unit.listener;

import com.pisproject.lawtextdb.listener.LawTextListener;
import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.service.implementation.PrimarySequenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LawTextListenerTests {

    PrimarySequenceServiceImpl seq;

    @BeforeEach
    void createLawTextListener(){
        seq = Mockito.mock(PrimarySequenceServiceImpl.class);
    }

    @Test
    void testLawTextListener(){
        LawTextListener listener = new LawTextListener(seq);
        assertNotEquals(new Object(), listener);
    }

    @Test
    void testOnBeforeConvert(){
        LawTextListener listener = new LawTextListener(seq);
        BeforeConvertEvent<LawText> event = Mockito.mock(BeforeConvertEvent.class);
        when(event.getSource()).thenReturn(new LawText());
        listener.onBeforeConvert(event);
        assertEquals(0, event.getSource().getId());
    }
}
