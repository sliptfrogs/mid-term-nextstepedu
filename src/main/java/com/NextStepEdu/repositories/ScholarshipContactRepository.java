package com.NextStepEdu.repositories;

import com.NextStepEdu.dto.responses.ScholarshipContactResponse;
import com.NextStepEdu.models.ScholarshipContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ScholarshipContactRepository extends JpaRepository<ScholarshipContactModel, Integer> {

    List<ScholarshipContactModel> findByScholarshipId(Integer id);

}
