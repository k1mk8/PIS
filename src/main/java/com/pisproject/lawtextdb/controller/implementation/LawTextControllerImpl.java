package com.pisproject.lawtextdb.controller.implementation;

import com.pisproject.lawtextdb.controller.LawTextController;
import com.pisproject.lawtextdb.controller.UserController;
import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.service.LawTextService;
import com.pisproject.lawtextdb.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/")
public class LawTextControllerImpl implements LawTextController {

    @Autowired
    LawTextService lawTextService;
    @Autowired
    UserAuthService authService;

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
    @GetMapping("/lawTexts/accepted")
    public List<LawText> getAccepted() {
        return lawTextService.getAccepted();
    }

    @Override
    @GetMapping("/lawTexts/notAccepted")
    public List<LawText> getNotAccepted() {
        return lawTextService.getNotAccepted();
    }

    @Override
    @PostMapping("/lawTexts/accept/{id}")
    public String acceptLawText(@PathVariable("id") int id, @RequestBody UserController.AuthRequest req) {
        if(!authService.checkIfTokenIsValid(req.username, req.token)){
            return "Could not authenticate admin user";
        }
        return lawTextService.acceptLawText(id);
    }

    @Override
    @PostMapping("/lawTexts/reject/{id}")
    public String deleteLawText(@PathVariable("id") int id, @RequestBody UserController.AuthRequest req) {
        if(!authService.checkIfTokenIsValid(req.username, req.token)){
            return "Could not authenticate admin user";
        }
        return lawTextService.deleteLawText(id);
    }

    @Override
    @GetMapping("/lawTexts/findById/{id}")
    public Optional<LawText> getLawTextById(@PathVariable("id") int id) {
        return lawTextService.getLawTextById(id);
    }

    @Override
    @GetMapping("/lawTexts/{id}.pdf")
    public String getLawTextByIdToDisplay(@PathVariable("id") int id) {
        return lawTextService.getLawTextByIdToDisplay(id);
    }

    @Override
    @GetMapping("/lawTexts/findByName/{name}")
    public ArrayList<Optional<LawText>> getLawTextByName(@PathVariable("name") String name) {
        return lawTextService.getLawTextByName(name);
    }

    @Override
    @GetMapping("/lawTexts/findByRawText/{rawText}")
    public ArrayList<Optional<LawText>> getLawTextByRawText(@PathVariable("rawText") String rawText) {
        return lawTextService.getLawTextByRawText(rawText);
    }

    @Override
    @PostMapping("lawTexts/add")
    public LawText addLawText(@RequestBody LawText newLawText) {
        return lawTextService.addLawText(newLawText);
    }

    @Override
    @PostMapping("lawTexts/uploadFile")
    public LawText addLawText(@RequestBody MultipartFile file) {
        return lawTextService.addLawText(file);
    }

    @Override
    @DeleteMapping("lawTexts/deleteAll")
    public String deleteAllLawTexts() {
        lawTextService.deleteAllLawTexts();
        return "Deleted all files.";
    }
}
