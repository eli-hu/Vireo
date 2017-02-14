package org.tdl.vireo.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tdl.vireo.model.FieldValue;
import org.tdl.vireo.model.SubmissionFieldProfile;
import org.tdl.vireo.model.repo.FieldValueRepo;
import org.tdl.vireo.model.repo.custom.FieldValueRepoCustom;

public class FieldValueRepoImpl implements FieldValueRepoCustom {

    @Autowired
    private FieldValueRepo fieldValueRepo;

    @Override
    public FieldValue create(SubmissionFieldProfile submissionFieldProfile) {    	
        return fieldValueRepo.save(new FieldValue(submissionFieldProfile));
    }

}
