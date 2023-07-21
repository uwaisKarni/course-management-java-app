package com.course.service.imp;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.domain.Student;
import com.course.domain.Teacher;
import com.course.dto.request.CourseRequestDTO;
import com.course.dto.respone.*;
import com.course.mapper.CourseMapper;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import com.course.repository.StudentRepository;
import com.course.repository.TeacherRepository;
import com.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImp implements CourseService {

    @Autowired
     CourseRepository courseRepository;

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ExamRepository examRepository;

    @Override
    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
      log.info("Inside addCourse Method");
        CourseMapper courseMapper = new CourseMapper();
        Course course = courseMapper.courseDtoToEntity( courseRequestDTO);
        if(courseRequestDTO.getTeacherId()!=null) {
            Optional<Teacher> teacher = teacherRepository.findById(courseRequestDTO.getTeacherId());
            if (!teacher.isPresent()) {
                throw new ResourceNotFoundException("Teacher","id",courseRequestDTO.getTeacherId());
            }
            course.setTeacher(teacher.get());
        }

       ;
        Course savedCourse = courseRepository.save(course);
        CourseResponseDTO response = new CourseResponseDTO();
        response.setId(savedCourse.getCourseId());
        response.setMsg("Course has been added!");
        return response;
    }

    @Override
    public AssignTeacherResponeDTO assignTeacher(Long courseId,Long teacherId) {

        Optional<Course> course = courseRepository.findById(courseId);

        if(!course.isPresent()){
            throw new ResourceNotFoundException("Course","id",courseId);
        }
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(!teacher.isPresent()){
            throw new ResourceNotFoundException("Teacher","id",teacherId);
        }

        course.get().setTeacher(teacher.get());
        courseRepository.save(course.get());
        AssignTeacherResponeDTO dto = new AssignTeacherResponeDTO();
        dto.setMsg("Teacher has been assigned!");
        return dto;
    }

    @Override
    public List<CourseMarksheetResponseDTO> generateCourseMarksheet(Long courseId) {

        Optional<Course> course = courseRepository.findById(courseId);

        if(!course.isPresent()){
            throw new ResourceNotFoundException("Course","id",courseId);
        }
        Course c = course.get();
        List<Student> students = c.getStudents();
        List<CourseMarksheetResponseDTO> response = new ArrayList<>();

        for(Student s:students){
           Integer obtainMarks = examRepository.getObtainMarksByStudentAndCourse(s.getStudentId(),courseId);
            CourseMarksheetResponseDTO responseDTO =new CourseMarksheetResponseDTO();
            responseDTO.setStudentId(s.getStudentId());
            responseDTO.setFirstName(s.getFirstName());
            responseDTO.setLastName(s.getLastName());
            responseDTO.setEmail(s.getEmail());
            responseDTO.setCourseTitle(c.getCourseTitle());
            responseDTO.setMaximumMarks(c.getMaximumMarks());
            responseDTO.setObtainMarks(obtainMarks);
            response.add(responseDTO);
        }
        return response;
    }

    @Override
    public CourseStudentDetailsResponseDTO getCourseStudentDetails(Long id) {

        CourseStudentDetailsResponseDTO  response = new CourseStudentDetailsResponseDTO();
        Optional<Course> courseEntity = courseRepository.findById(id);
        Course course =null;
        if(courseEntity.isPresent()) {
             course = courseEntity.get();
         }
        List<String> studentNames = new ArrayList<>();
        if(course!=null &&  (!course.getStudents().isEmpty())) {
             studentNames = course.getStudents().stream()
                    .map(student -> student.getFirstName() + " " + student.getLastName()).collect(Collectors.toList());

        }
        response.setCourseName(course.getCourseName());
        response.setCourseTitle(course.getCourseTitle());

        if (course.getTeacher() != null) {
            response.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        }
        response.setStudentNames(studentNames);
        return response;
    }

    @Override
    public List<CourseViewResponseDTO> getCourseViewList() {
        List<CourseViewResponseDTO> response = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();

        for(Course c:courses){
            CourseViewResponseDTO dto = new CourseViewResponseDTO();
            dto.setCourseName(c.getCourseName());
            dto.setId(c.getCourseId());
            dto.setCourseTitle(c.getCourseTitle());
            if(c.getTeacher()!=null)
                dto.setTeacherName(c.getTeacher().getFirstName()+ " "+c.getTeacher().getLastName());
            dto.setNoOfStudents(c.getStudents().size());
            response.add(dto);
        }
        return response;
    }

}
