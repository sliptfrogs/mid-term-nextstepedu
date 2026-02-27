package com.NextStepEdu.mappers;

import com.NextStepEdu.dto.requests.UniversityRequest;
import com.NextStepEdu.dto.responses.FacultyResponse;
import com.NextStepEdu.dto.responses.UniversityResponse;
import com.NextStepEdu.models.FacultyModel;
import com.NextStepEdu.models.UniversityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UniversityMapper {

    UniversityResponse toResponse(UniversityModel model);
    default List<FacultyResponse> mapFaculties(List<FacultyModel> faculties) {
        if (faculties == null) return java.util.Collections.emptyList();
        return faculties.stream()
                .map(faculty -> new FacultyResponse(
                        faculty.getId(),
                        faculty.getName(),
                        faculty.getDescription(),
                        null // or set universities if needed
                ))
                .collect(Collectors.toList());
    }
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "faculties", ignore = true)
    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "scholarships", ignore = true)
    @Mapping(target = "logoUrl", ignore = true)
    @Mapping(target = "coverImageUrl", ignore = true)
    UniversityModel toModel(UniversityRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "faculties", ignore = true)
    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "scholarships", ignore = true)
    @Mapping(target = "logoUrl", ignore = true)
    @Mapping(target = "coverImageUrl", ignore = true)
    void updateModel(UniversityRequest request, @MappingTarget UniversityModel model);
}
