package com.course.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {

    private String firstName;
    private String lastName;
    private String code;
    private String email;

}
