package com.pisproject.lawtextdb.resource;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.service.LawTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LawTextResource {

    @Autowired
    LawTextService service;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/lawTexts")
    public List<LawText> getAll() {
        return service.getAll();
    }

    @GetMapping("/lawTexts/{id}")
    public Optional<LawText> getLawTextById(@PathVariable("id") int id) {
        return service.getLawTextById(id);
    }

    @PostMapping("lawTexts/add")
    public LawText addLawText(@RequestBody LawText newLawText) {
        return service.addLawText(newLawText);
    }

    @DeleteMapping("lawTexts/deleteAll")
    public String deleteAllLawTexts() {
        return service.deleteAllLawTexts();
    }
}
