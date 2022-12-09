package com.pisproject.lawtextdb.resource;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.service.LawTextService;
import com.pisproject.lawtextdb.service.PrimarySequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LawTextResource {

    @Autowired
    LawTextService lawTextService;
    @Autowired
    PrimarySequenceService primarySequenceService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/lawTexts")
    public List<LawText> getAll() {
        return lawTextService.getAll();
    }

    @GetMapping("/lawTexts/{id}")
    public Optional<LawText> getLawTextById(@PathVariable("id") int id) {
        return lawTextService.getLawTextById(id);
    }

    @PostMapping("lawTexts/add")
    public LawText addLawText(@RequestBody LawText newLawText) {
        return lawTextService.addLawText(newLawText);
    }

    @DeleteMapping("lawTexts/deleteAll")
    public String deleteAllLawTexts() {
        primarySequenceService.resetSequence();
        return lawTextService.deleteAllLawTexts();
    }
}
