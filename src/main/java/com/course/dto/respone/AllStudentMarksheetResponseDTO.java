package com.course.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllStudentMarksheetResponseDTO {

     @JsonProperty("student_Name")
     private String StudentName;
     @JsonProperty("student_id")
     private Long StudentId;

     @JsonProperty("courses")
     private List<CourseDetailsResponseDTO> courseDetailsResponseDTOS;
}
