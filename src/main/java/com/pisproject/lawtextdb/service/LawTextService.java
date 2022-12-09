package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.LawText;

import java.util.List;
import java.util.Optional;

public interface LawTextService {
    List<LawText> getAll();
    Optional<LawText> getLawTextById(int id);
    LawText addLawText(LawText newLawText);
    String deleteAllLawTexts();
}