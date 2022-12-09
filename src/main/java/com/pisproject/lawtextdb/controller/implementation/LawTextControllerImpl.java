package com.pisproject.lawtextdb.controller.implementation;

import com.pisproject.lawtextdb.controller.LawTextController;
import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.service.LawTextService;
import com.pisproject.lawtextdb.service.PrimarySequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LawTextControllerImpl implements LawTextController {

    @Autowired
    LawTextService lawTextService;
    @Autowired
    PrimarySequenceService primarySequenceService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @Override
    @GetMapping("/lawTexts")
    public List<LawText> getAll() {
        return lawTextService.getAll();
    }

    @Override
    @GetMapping("/lawTexts/{id}")
    public Optional<LawText> getLawTextById(@PathVariable("id") int id) {
        return lawTextService.getLawTextById(id);
    }

    @Override
    @PostMapping("lawTexts/add")
    public LawText addLawText(@RequestBody LawText newLawText) {
        return lawTextService.addLawText(newLawText);
    }

    @Override
    @DeleteMapping("lawTexts/deleteAll")
    public String deleteAllLawTexts() {
        primarySequenceService.resetSequence();
        return lawTextService.deleteAllLawTexts();
    }
}
