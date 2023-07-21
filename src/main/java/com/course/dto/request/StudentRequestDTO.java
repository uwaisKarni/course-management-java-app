package com.course.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
	
	   private String firstName;
	   private String lastName;
	   private String email;
}
