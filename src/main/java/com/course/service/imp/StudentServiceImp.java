package com.course.service.imp;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.dto.respone.*;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.course.domain.Student;
import com.course.dto.request.StudentRequestDTO;
import com.course.mapper.StudentMapper;
import com.course.repository.StudentRepository;
import com.course.service.StudentService;

import java.util.*;

@Service
public class StudentServiceImp implements StudentService{
 
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ExamRepository examRepository;

	@Value("${maximum.number.students}")
	private int maxNumberOfStudents;

	@Value("${student.enrolled.in.max.courses}")
	private int studentEnrolledInMaxCourses;
	
	@Override
	public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDto) {

		StudentMapper studentMapper = new StudentMapper();
		Student student = studentMapper.convertStudentRequestDtoToStudent(studentRequestDto);
		Student std = studentRepository.save(student);
		StudentResponseDTO response = new StudentResponseDTO(); 
		response.setId(std.getStudentId());
		response.setMsg("Student has been added");
		return response;
	}

	@Override
	public UpdateResourseResponseDTO updateStudent(StudentRequestDTO studentRequestDto,Long studentId) {

		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()){
			throw new ResourceNotFoundException("Student","id",studentId);
		}
        Student s = student.get();
		s.setFirstName(studentRequestDto.getFirstName());
		s.setLastName(studentRequestDto.getLastName());
		s.setEmail(studentRequestDto.getEmail());
		studentRepository.save(s);
		UpdateResourseResponseDTO dto = new UpdateResourseResponseDTO();
		dto.setMsg("Student updated Successfully!");
		return dto;
	}

	@Override
	public DeleteResourseResponeDTO deletStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()){
			throw new ResourceNotFoundException("Student","id",studentId);
		}
		Student s = student.get();
		studentRepository.delete(s);
		DeleteResourseResponeDTO dto = new DeleteResourseResponeDTO();
		dto.setMsg("Student delete Successfully!");
		return dto;
	}

	@Override
	public List<Student> allStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student getStudent(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()){
			throw new ResourceNotFoundException("Student","id",id);
		}
		return student.get();
	}

	@Override
	public EnrollCourseResponeDTO enrollCourse(Long courseId,Long studentId) {

		Optional<Course> course = courseRepository.findById(courseId);

		if(!course.isPresent()){
			throw new ResourceNotFoundException("Course","id",courseId);
		}
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()){
			throw new ResourceNotFoundException("Student","id",studentId);
		}

		Course c = course.get();
		List<Student> studentList = c.getStudents();
		EnrollCourseResponeDTO dto =new EnrollCourseResponeDTO();
		Optional<Student> alreadyEnrolled = studentList.stream().filter(s -> s.getStudentId().equals(studentId)).findAny();
        if(alreadyEnrolled.isPresent()){
			dto.setMsg("Student Already Enrolled!");
			return dto ;
		}

		if(studentList.size()==maxNumberOfStudents){
			dto.setMsg("Already Maximum no of Students Enrolled!");
			return dto ;
		}
		Student s = student.get();
		if(s.getCourses().size() >=studentEnrolledInMaxCourses ){
			dto.setMsg("Student Already Enrolled in Maximimum no of Courses!");
			return dto ;
		}

		studentList.add(s);

		c.setStudents(studentList);
		List<Course> courses = s.getCourses();
		courses.add(c);
		s.setCourses(courses);

		studentRepository.save(s);
		dto.setMsg("Student Successfully Enrolled in Course");
		return dto;
	}

	@Override
	public List<AllStudentMarksheetResponseDTO> generateAllStudentsMarksheet() {

		List<AllStudentMarksheetResponseDTO> response = new ArrayList<>();
		List<Student> students = studentRepository.findAll();

		for(Student s:students){

			AllStudentMarksheetResponseDTO  responseDTO = new AllStudentMarksheetResponseDTO();
			responseDTO.setStudentName(s.getFirstName()+" "+s.getLastName());
            responseDTO.setStudentId(s.getStudentId());
			List<Course> courses = s.getCourses();
			List<CourseDetailsResponseDTO> courseDetailsResponseDTOList = new ArrayList<>();
			for(Course c:courses){
				CourseDetailsResponseDTO courseDetailsResponseDTO = new CourseDetailsResponseDTO();
				courseDetailsResponseDTO.setCourseTitle(c.getCourseTitle());
				courseDetailsResponseDTO.setMaximumMarks(c.getMaximumMarks());
				Integer obtainMarks = examRepository.getObtainMarksByStudentAndCourse(s.getStudentId(),c.getCourseId());
                courseDetailsResponseDTO.setObtainMarks(obtainMarks);
				courseDetailsResponseDTOList.add(courseDetailsResponseDTO);
			}
			responseDTO.setCourseDetailsResponseDTOS(courseDetailsResponseDTOList);
			response.add(responseDTO);
		}
		Collections.sort(response, Comparator.comparing(AllStudentMarksheetResponseDTO::getStudentName));

		return response;
	}

}
