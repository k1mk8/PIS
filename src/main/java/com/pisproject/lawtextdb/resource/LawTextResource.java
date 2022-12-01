package com.pisproject.lawtextdb.resource;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.repository.LawTextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/lawTexts")
public class LawTextResource {

    private final LawTextRepository repository;

    public LawTextResource(LawTextRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<LawText> getAll() {
        return repository.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<LawText> getByIt(@PathVariable("id") int id) {
        return repository.findById(id);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
