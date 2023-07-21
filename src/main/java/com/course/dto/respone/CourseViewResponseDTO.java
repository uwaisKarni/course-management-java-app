package com.course.dto.respone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseViewResponseDTO {

    @JsonIgnore
    private Long id;

    @JsonProperty("course_Name")
    private String courseName;

    @JsonProperty("teacher_Name")
    private String teacherName;

    @JsonProperty("total_Students")
    private int noOfStudents;

    @JsonProperty("courseTitle")
    private String courseTitle;




}
