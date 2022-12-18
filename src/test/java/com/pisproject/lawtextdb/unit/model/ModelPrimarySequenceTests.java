package com.pisproject.lawtextdb.unit.model;

import com.pisproject.lawtextdb.model.mongo.PrimarySequence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelPrimarySequenceTests {
    @Test
    void testPrimarySequence(){
        PrimarySequence ps = new PrimarySequence();
        ps.setId("test");
        ps.setSeq(12);
        assertEquals("test", ps.getId());
        assertEquals(12, ps.getSeq());
    }
}
