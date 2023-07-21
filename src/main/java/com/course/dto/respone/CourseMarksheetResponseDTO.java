package com.course.dto.respone;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMarksheetResponseDTO {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String courseTitle;
    private Integer maximumMarks;
    private Integer obtainMarks;
}
