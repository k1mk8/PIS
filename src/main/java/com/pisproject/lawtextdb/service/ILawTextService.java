package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.LawText;

import java.util.List;
import java.util.Optional;

public interface ILawTextService {
    List<LawText> getAll();
    Optional<LawText> getById(int id);
    LawText addLawText(LawText newLawText);
}
