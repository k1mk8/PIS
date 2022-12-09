package com.pisproject.lawtextdb.service;

public interface PrimarySequenceService {
    Integer getNextValue();
    void resetSequence();
}
