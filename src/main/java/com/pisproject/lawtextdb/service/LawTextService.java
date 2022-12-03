package com.pisproject.lawtextdb.service;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.repository.LawTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawTextService implements ILawTextService{

    @Autowired
    private LawTextRepository repository;

    @Override
    public List<LawText> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LawText> getById(int id) {
        return repository.findById(id);
    }

//    @Override
//    public Optional<LawText> getById(int id) {
//        return repository.findById(id).orElse(new LawText());
//    }

    @Override
    public LawText addLawText(LawText newLawText) {
        return repository.save(newLawText);
    }
}
