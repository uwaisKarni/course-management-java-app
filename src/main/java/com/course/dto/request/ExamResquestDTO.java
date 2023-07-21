package com.course.dto.request;

import lombok.*;
@Data
@Getter
@Setter
@NoArgsConstructor
public class ExamResquestDTO {

    private Long courseId;
    private Long studentId;
    private Integer maximumMarks;
    private Integer obtainMarks;



}
