package com.course.controller;

import org.springframework.hateoas.Link;
import com.course.dto.request.CourseRequestDTO;
import com.course.dto.request.ExamResquestDTO;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.respone.*;
import com.course.service.CourseService;
import com.course.service.ExamService;
import com.course.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.dto.request.StudentRequestDTO;
import com.course.service.StudentService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin
@Slf4j
public class AdminController {
	
	
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	CourseService courseService;
	@Autowired
	ExamService examService;
	
	
	@PostMapping("/create-student")
	public ResponseEntity<StudentResponseDTO> createStudent (@RequestBody StudentRequestDTO studentrequest){
       log.info("createStudent method started: ");
		StudentResponseDTO studentResponse = studentService.addStudent(studentrequest);
		return new ResponseEntity<>(studentResponse,HttpStatus.CREATED);
	}

	@PostMapping("/create-teacher")
	public ResponseEntity<TeacherResponseDTO> createTeacher (@RequestBody TeacherRequestDTO teacherRequestDTO){
		log.info("createTeacher method started: ");
		TeacherResponseDTO teacherResponse = teacherService.addTeacher(teacherRequestDTO);
		return new ResponseEntity<>(teacherResponse,HttpStatus.CREATED);
	}

	@PostMapping("/create-course")
	public ResponseEntity<CourseResponseDTO> createCourse (@RequestBody CourseRequestDTO courseRequestDTO){

		CourseResponseDTO courseResponse = courseService.addCourse(courseRequestDTO);
		return new ResponseEntity<>(courseResponse,HttpStatus.CREATED);
	}

	@PostMapping("/assign-teacher")
	public ResponseEntity<AssignTeacherResponeDTO> assignTeacher(@RequestParam Long courseId,@RequestParam Long teacherId){
		AssignTeacherResponeDTO response= courseService.assignTeacher(courseId,teacherId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@GetMapping("/course-marksheet")
	public ResponseEntity<List<CourseMarksheetResponseDTO>> courseMarksheet(@RequestParam Long courseId){
		List<CourseMarksheetResponseDTO> response = courseService.generateCourseMarksheet(courseId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PostMapping("/saveMarks")
	public ResponseEntity<SaveMarksResponeDTO> assignMarks(@RequestBody ExamResquestDTO examResquestDTO){
		SaveMarksResponeDTO response= examService.saveStudentMarks(examResquestDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@GetMapping("/all-course")
	public ResponseEntity<List<CourseViewResponseDTO>> allCourses(){
		List<CourseViewResponseDTO> response = courseService.getCourseViewList();

		for(CourseViewResponseDTO c:response){

			Link courseLink = linkTo(methodOn(AdminController.class)
					.getCourseDetails(c.getId()))
					.withRel("courseDetails");
			c.setCourseName(courseLink.getHref());
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@GetMapping("/get-course-details/{id}")
	public ResponseEntity<CourseStudentDetailsResponseDTO> getCourseDetails(@PathVariable Long id){
		CourseStudentDetailsResponseDTO response = courseService.getCourseStudentDetails(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
