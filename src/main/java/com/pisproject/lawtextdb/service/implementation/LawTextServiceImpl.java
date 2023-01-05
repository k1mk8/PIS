package com.pisproject.lawtextdb.service.implementation;

import com.pisproject.lawtextdb.model.mongo.LawText;
import com.pisproject.lawtextdb.model.solr.SolrLawText;
import com.pisproject.lawtextdb.repository.mongo.LawTextRepository;
import com.pisproject.lawtextdb.repository.solr.SolrLawTextRepository;
import com.pisproject.lawtextdb.service.LawTextService;
import com.pisproject.lawtextdb.service.PrimarySequenceService;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class LawTextServiceImpl implements LawTextService {

    @Autowired
    private LawTextRepository lawTextRepository;

    @Autowired
    private SolrLawTextRepository solrLawTextRepository;

    @Autowired
    private PrimarySequenceService primarySequenceService;

    @Override
    public List<LawText> getAll() {
        return lawTextRepository.findAll();
    }

    @Override
    public List<LawText> getAccepted() {
        List<LawText> lawTexts = lawTextRepository.findAll();
        List<LawText> acceptedLawTexts = new ArrayList<>();

        for (LawText lawText : lawTexts) {
            if (lawText.isAccepted())
                acceptedLawTexts.add(lawText);
        }

        return acceptedLawTexts;
    }

    @Override
    public List<LawText> getNotAccepted() {
        List<LawText> lawTexts = lawTextRepository.findAll();
        List<LawText> notAcceptedLawTexts = new ArrayList<>();

        for (LawText lawText : lawTexts) {
            if (!lawText.isAccepted())
                notAcceptedLawTexts.add(lawText);
        }

        return notAcceptedLawTexts;
    }

    @Override
    public Optional<LawText> getLawTextById(int id) {
        return lawTextRepository.findById(id);
    }

    @Override
    public ArrayList<Optional<LawText>> getLawTextByName(String name) {
        List<SolrLawText> solrLawTexts = solrLawTextRepository.findByName(name);
        ArrayList<Optional<LawText>> lawTexts = new ArrayList<>();

        for (SolrLawText i : solrLawTexts) {
            lawTexts.add(getLawTextById(i.getLawTextId()));
        }

        return lawTexts;
    }

    @Override
    public ArrayList<Optional<LawText>> getLawTextByRawText(String rawText) {
        List<SolrLawText> solrLawTexts = solrLawTextRepository.findByRawText(rawText);
        ArrayList<Optional<LawText>> lawTexts = new ArrayList<>();

        for (SolrLawText i : solrLawTexts) {
            lawTexts.add(getLawTextById(i.getLawTextId()));
        }

        return lawTexts;
    }

    public void addLawTextToSolr(LawText lawText, String rawText) {
        SolrLawText solrLawText = new SolrLawText();
        solrLawText.setName(lawText.getName());
        solrLawText.setRawText(rawText);
        solrLawText.setLawTextId(lawText.getId());
        solrLawTextRepository.save(solrLawText);
    }

    @Override
    public LawText addLawText(LawText newLawText) {
        return lawTextRepository.save(newLawText);
    }

    @Override
    public LawText addLawText(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!Objects.equals(extension, "pdf"))
            return new LawText();

        try {
            LawText newLawText = new LawText(file);
            String rawText = extractTextFromPdf(file);
            lawTextRepository.save(newLawText);
            addLawTextToSolr(newLawText, rawText);
            return newLawText;
        } catch (Exception e) {
            e.printStackTrace();
            return new LawText();
        }
    }

    private String extractTextFromPdf(MultipartFile file) throws IOException {
        File temp = File.createTempFile("law_text", ".pdf");
        file.transferTo(temp);
        PDDocument doc = PDDocument.load(temp);
        String txt = new PDFTextStripper().getText(doc);

        doc.close();
        Files.deleteIfExists(temp.toPath());

        return txt;
    }

    private void updateReferences() {
        List<LawText> lawTexts = lawTextRepository.findAll();

        for (LawText lawText : lawTexts) {
            if (!lawText.isAccepted())
                continue;

            List<SolrLawText> solrLawTexts =
                    solrLawTextRepository.findByRawText(lawText.getName());

            for (SolrLawText solrLawText : solrLawTexts) {
                Optional<LawText> temp = lawTextRepository.findById(solrLawText.getLawTextId());
                if (temp.isEmpty())
                    return;

                LawText tempLawText = temp.get();
                if (!tempLawText.isAccepted())
                    continue;

                if (!Objects.equals(tempLawText.getId(), lawText.getId())) {
                    tempLawText.updateReferences(lawText.getId());
                }
            }
        }
    }

    @Override
    public void deleteAllLawTexts() {
        lawTextRepository.deleteAll();
        solrLawTextRepository.deleteAll();
        primarySequenceService.resetSequence();
    }
}
