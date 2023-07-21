package com.course.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsResponseDTO {

    @JsonProperty("course_Title")
    private String courseTitle;
    @JsonProperty("maximum_Marks")
    private Integer maximumMarks;
    @JsonProperty("obtain_Marks")
    private Integer obtainMarks;


}
