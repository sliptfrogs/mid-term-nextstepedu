package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.ScholarshipContactRequest;
import com.NextStepEdu.dto.responses.ScholarshipContactResponse;
import com.NextStepEdu.models.ScholarshipContactModel;

import java.util.List;

public interface ScholarshipContactService {

    List<ScholarshipContactResponse> findAll();

    List<ScholarshipContactModel> findByScholarshipId(Integer scholarshipId);

    ScholarshipContactResponse findById(Integer id);

    ScholarshipContactModel create(ScholarshipContactRequest request);

    ScholarshipContactModel update(Integer id, ScholarshipContactRequest request);

    void delete(Integer id);
}
