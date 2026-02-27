package com.NextStepEdu.mappers;


import com.NextStepEdu.dto.responses.ScholarshipContactResponse;
import com.NextStepEdu.models.ScholarshipContactModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScholarshipContactMapper {

    @Mapping(source = "scholarship.id", target = "scholarship_id")
    ScholarshipContactResponse toScholarshipContactResponse(
            ScholarshipContactModel scholarshipContactModel
    );

    @Mapping(source = "scholarship.id", target = "scholarship_id")
    List<ScholarshipContactResponse> toScholarshipContactResponseList(List<ScholarshipContactModel> scholarshipContactModelList);
}
