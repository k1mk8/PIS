package com.pisproject.lawtextdb.controller;

import com.pisproject.lawtextdb.model.mongo.LawText;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LawTextController {
    List<LawText> getAll();
    List<LawText> getAccepted();
    List<LawText> getNotAccepted();
    String acceptLawText(@PathVariable("id") int id, @RequestBody UserController.AuthRequest req);
    String deleteLawText(@PathVariable("id") int id, @RequestBody UserController.AuthRequest req);
    Optional<LawText> getLawTextById(@PathVariable("id") int id);
    String getLawTextByIdToDisplay(@PathVariable("id") int id);
    ArrayList<Optional<LawText>> getLawTextByName(@PathVariable("name") String name);
    ArrayList<Optional<LawText>> getLawTextByRawText(@PathVariable("rawText") String rawText);
    LawText addLawText(@RequestBody LawText newLawText);
    LawText addLawText(@RequestBody MultipartFile file);
    String deleteAllLawTexts();
}
