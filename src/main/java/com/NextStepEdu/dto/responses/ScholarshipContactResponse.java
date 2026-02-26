package com.NextStepEdu.dto.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipContactResponse {
    private Integer id;
    private String label;
    private String email;
    private String phone;
    private String websiteUrl;
    private Integer scholarship_id;
}
