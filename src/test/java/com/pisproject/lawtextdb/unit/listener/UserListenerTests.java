package com.pisproject.lawtextdb.unit.listener;

import com.pisproject.lawtextdb.listener.LawTextListener;
import com.pisproject.lawtextdb.listener.UserListener;
import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.model.mongo.User;
import com.pisproject.lawtextdb.service.implementation.PrimarySequenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class UserListenerTests {

    PrimarySequenceServiceImpl seq;

    @BeforeEach
    void createUserListener(){
        seq = Mockito.mock(PrimarySequenceServiceImpl.class);
    }

    @Test
    void testUserListener(){
        UserListener listener = new UserListener(seq);
        assertNotEquals(new Object(), listener);
    }

    @Test
    void testOnBeforeConvert(){
        UserListener listener = new UserListener(seq);
        BeforeConvertEvent<User> event = Mockito.mock(BeforeConvertEvent.class);
        when(event.getSource()).thenReturn(new User());
        listener.onBeforeConvert(event);
        assertEquals(0, event.getSource().getId());
    }
}