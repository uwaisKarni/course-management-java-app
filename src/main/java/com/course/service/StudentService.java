package com.course.service;

import com.course.domain.Student;
import com.course.dto.request.StudentRequestDTO;
import com.course.dto.respone.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

	 public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDto);
	public UpdateResourseResponseDTO updateStudent(StudentRequestDTO studentRequestDto,Long studentId);
    public DeleteResourseResponeDTO deletStudent(Long StudentId);

	public List<Student> allStudents();

	public Student getStudent(Long id);
	public EnrollCourseResponeDTO enrollCourse(Long courseId,Long studentId);

	public List<AllStudentMarksheetResponseDTO> generateAllStudentsMarksheet();


}
