package com.pisproject.lawtextdb.service.implementation;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.repository.LawTextRepository;
import com.pisproject.lawtextdb.service.LawTextService;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
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
    public LawText addLawText(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!Objects.equals(extension, "pdf"))
            return new LawText();

        try {
            LawText newLawText = new LawText(file);
            String rawText = extractTextFromPdf(file);
            newLawText.setRawText(rawText);
            return repository.save(newLawText);
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

    @Override
    public String deleteAllLawTexts() {
        repository.deleteAll();
        return "Deleted all files.";
    }
}
