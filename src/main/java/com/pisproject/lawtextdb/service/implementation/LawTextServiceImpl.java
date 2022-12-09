package com.pisproject.lawtextdb.service.implementation;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.repository.LawTextRepository;
import com.pisproject.lawtextdb.service.LawTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawTextServiceImpl implements LawTextService {

    @Autowired
    private LawTextRepository repository;

    @Override
    public List<LawText> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LawText> getLawTextById(int id) {
        return repository.findById(id);
    }

    @Override
    public LawText addLawText(LawText newLawText) {
        return repository.save(newLawText);
    }

    @Override
    public String deleteAllLawTexts() {
        repository.deleteAll();
        return "Deleted all files.";
    }
}
