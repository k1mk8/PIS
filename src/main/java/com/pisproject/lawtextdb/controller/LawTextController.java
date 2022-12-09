package com.pisproject.lawtextdb.controller;

import com.pisproject.lawtextdb.model.LawText;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface LawTextController {
    List<LawText> getAll();
    Optional<LawText> getLawTextById(@PathVariable("id") int id);
    LawText addLawText(@RequestBody LawText newLawText);
    String deleteAllLawTexts();
}