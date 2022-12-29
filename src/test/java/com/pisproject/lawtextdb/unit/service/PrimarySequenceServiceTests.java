package com.pisproject.lawtextdb.unit.service;

import com.pisproject.lawtextdb.service.implementation.PrimarySequenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimarySequenceServiceTests {
    private PrimarySequenceServiceImpl service;

    @BeforeEach
    void setUpService(){
        MongoOperations mongoOperations = Mockito.mock(MongoOperations.class);
        service = new PrimarySequenceServiceImpl(mongoOperations);
    }

    @Test
    void testGetNextValue(){
        Integer result = service.getNextValue();
        assertEquals(10000, result);
    }

    @Test
    void testResetSequence(){
        service.resetSequence();
    }
}
