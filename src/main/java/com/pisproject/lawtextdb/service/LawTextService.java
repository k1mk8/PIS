package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.mongo.LawText;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LawTextService {
    List<LawText> getAll();
    List<LawText> getAccepted();
    List<LawText> getNotAccepted();
    Optional<LawText> getLawTextById(int id);
    ArrayList<Optional<LawText>> getLawTextByName(String name);
    ArrayList<Optional<LawText>> getLawTextByRawText(String rawText);
    LawText addLawText(LawText newLawText);
    LawText addLawText(MultipartFile file);
    void deleteAllLawTexts();
}
