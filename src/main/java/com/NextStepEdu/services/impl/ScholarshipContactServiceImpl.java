package com.NextStepEdu.services.impl;

import com.NextStepEdu.dto.requests.ScholarshipContactRequest;
import com.NextStepEdu.dto.responses.ScholarshipContactResponse;
import com.NextStepEdu.mappers.ScholarshipContactMapper;
import com.NextStepEdu.models.ScholarshipContactModel;
import com.NextStepEdu.models.ScholarshipModel;
import com.NextStepEdu.repositories.ScholarshipContactRepository;
import com.NextStepEdu.repositories.ScholarshipRepository;
import com.NextStepEdu.services.ScholarshipContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScholarshipContactServiceImpl implements ScholarshipContactService {

    private final ScholarshipContactRepository scholarshipContactRepository;
    private final ScholarshipRepository scholarshipRepository;
    private final ScholarshipContactMapper scholarshipContactMapper;

    @Override
    public List<ScholarshipContactResponse> findAll() {

//        return scholarshipContactRepository.findAll();

        return scholarshipContactMapper.toScholarshipContactResponseList(scholarshipContactRepository.findAll());
    }



    @Override
    public List<ScholarshipContactModel> findByScholarshipId(Integer scholarshipId) {
        return scholarshipContactRepository.findByScholarshipId(scholarshipId);
    }

    @Override
    public ScholarshipContactResponse findById(Integer id) {
        return scholarshipContactMapper.toScholarshipContactResponse(scholarshipContactRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Scholarship contact not found: " + id)));
    }

    @Override
    public ScholarshipContactModel create(ScholarshipContactRequest request) {
        ScholarshipModel scholarship = scholarshipRepository.findById(request.scholarshipId())
                .orElseThrow(() -> new RuntimeException("Scholarship not found: " + request.scholarshipId()));

        ScholarshipContactModel contact = new ScholarshipContactModel();
        contact.setLabel(request.label());
        contact.setEmail(request.email());
        contact.setPhone(request.phone());
        contact.setWebsiteUrl(request.websiteUrl());
        contact.setScholarship(scholarship);

        return scholarshipContactRepository.save(contact);
    }

    @Override
    public ScholarshipContactModel update(Integer id, ScholarshipContactRequest request) {
        ScholarshipContactModel contact = scholarshipContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scholarship contact not found: " + id));

        if (request.label() != null) contact.setLabel(request.label());
        if (request.email() != null) contact.setEmail(request.email());
        if (request.phone() != null) contact.setPhone(request.phone());
        if (request.websiteUrl() != null) contact.setWebsiteUrl(request.websiteUrl());

        if (request.scholarshipId() != null) {
            ScholarshipModel scholarship = scholarshipRepository.findById(request.scholarshipId())
                    .orElseThrow(() -> new RuntimeException("Scholarship not found: " + request.scholarshipId()));
            contact.setScholarship(scholarship);
        }

        return scholarshipContactRepository.save(contact);
    }

    @Override
    public void delete(Integer id) {
        ScholarshipContactModel contact = scholarshipContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scholarship contact not found: " + id));
        scholarshipContactRepository.delete(contact);
    }
}
