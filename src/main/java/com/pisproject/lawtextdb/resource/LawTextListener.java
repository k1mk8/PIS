package com.pisproject.lawtextdb.resource;

import com.pisproject.lawtextdb.model.LawText;
import com.pisproject.lawtextdb.service.PrimarySequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class LawTextListener extends AbstractMongoEventListener<LawText> {

    @Autowired
    private final PrimarySequenceService primarySequenceService;

    public LawTextListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<LawText> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
