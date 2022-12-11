package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.mongo.LawText;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface LawTextService {
    List<LawText> getAll();
    Optional<LawText> getLawTextById(int id);
    LawText addLawText(LawText newLawText);
    LawText addLawText(MultipartFile file);
    void deleteAllLawTexts();
}
