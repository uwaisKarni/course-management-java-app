package com.course.dto.respone;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentDetailsResponseDTO {

    @JsonProperty("course_Name")
    private String courseName;
    @JsonProperty("teacher_Name")
    private String teacherName;
    @JsonProperty("courseTitle")
    private String courseTitle;

    @JsonProperty("students")
    private List<String> studentNames;

}
