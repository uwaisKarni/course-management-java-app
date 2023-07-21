package com.course.mapper;

import com.course.domain.Student;
import com.course.dto.request.StudentRequestDTO;

public class StudentMapper {
	
	
	public  Student convertStudentRequestDtoToStudent (StudentRequestDTO studentRequestDTO) {
		
		  Student student = new Student();
		  student.setFirstName(studentRequestDTO.getFirstName());
		  student.setLastName(studentRequestDTO.getLastName());
		  student.setEmail(studentRequestDTO.getEmail());
		  return student;
		  	  
	}
	

}
