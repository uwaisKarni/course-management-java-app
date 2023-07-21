package com.course.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDTO {

    private String courseName;
    private String courseTitle;
    private Integer maximumMarks;
    private Long teacherId;



}
