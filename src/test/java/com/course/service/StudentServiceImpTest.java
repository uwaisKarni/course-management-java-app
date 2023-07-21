package com.course.service;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.dto.request.StudentRequestDTO;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.course.domain.Student;
import com.course.repository.StudentRepository;
import com.course.service.imp.StudentServiceImp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImpTest {

	@Mock
	StudentRepository studentRepository;

	@Mock
	CourseRepository courseRepository;

	@Mock
	ExamRepository examRepository;
	
	@InjectMocks
	StudentServiceImp studentServiceImpMock;
	 
	@Test
	public void addStudentTest() {

	//	Mockito.when(studentMapper.convertStudentRequestDtoToStudent(Mockito.any())).thenReturn(getStudent());
		Mockito.when(studentRepository.save(Mockito.any())).thenReturn(getStudent());
		studentServiceImpMock.addStudent(getStudentRequestDTO());
	}

	@Test
	public void updateStudentTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getStudent()));
		Mockito.when(studentRepository.save(Mockito.any())).thenReturn(getStudent());
		studentServiceImpMock.updateStudent(getStudentRequestDTO(),1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void updateStudentExceptionTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentServiceImpMock.updateStudent(getStudentRequestDTO(),1L);

	}

	@Test
	public void deleteStudentTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getStudent()));
		studentServiceImpMock.deletStudent(1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteStudentExceptionTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentServiceImpMock.deletStudent(1L);
	}

	@Test
	public void getStudentTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getStudent()));
		studentServiceImpMock.getStudent(1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getStudentExceptionTest() {
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentServiceImpMock.getStudent(1L);
	}

	@Test
	public void allStudentTest() {
		Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(getStudent()));
		studentServiceImpMock.allStudents();
	}

	@Test
	public void enrollCourseTest() {
		Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getStudent()));
		//Mockito.when(studentRepository.save(Mockito.any())).thenReturn(Optional.of(getStudent()));
		studentServiceImpMock.enrollCourse(1L,1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void enrollCourseExceptionTest(){
		Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentServiceImpMock.enrollCourse(1L,1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void enrollCourseStudentExceptionTest(){
		Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentServiceImpMock.enrollCourse(1L,1L);
	}

	@Test
	public void generateAllStudentsMarksheet() {
		Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(getStudent()));
		studentServiceImpMock.generateAllStudentsMarksheet();
	}


	public Student getStudent() {
		 Student student=new Student();
		student.setStudentId(1L);
		student.setFirstName("Sam");
		List<Course> courses = new ArrayList<>();
		Course c = new Course();
		c.setCourseId(1L);
		c.setCourseTitle("Test");
		courses.add(c);
		student.setCourses(courses);
		 return student;
	 }

	public Course getCourse(){
		Course course = new Course();
		course.setCourseTitle("Spring");
		List<Student> students = new ArrayList<>();
		Student s= new Student();
		s.setStudentId(1L);
		students.add(s);
		course.setStudents(students);
		return course;
	}

	 public StudentRequestDTO getStudentRequestDTO(){

		 StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
		 return  studentRequestDTO;

	 }

	

}
