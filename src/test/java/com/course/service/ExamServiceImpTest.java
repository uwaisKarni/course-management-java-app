package com.course.service;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.domain.Exam;
import com.course.domain.Student;
import com.course.dto.request.ExamResquestDTO;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import com.course.repository.StudentRepository;
import com.course.service.imp.ExamServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(JacocoExtension.class)
public class ExamServiceImpTest {


    @Mock
    CourseRepository courseRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    ExamRepository examRepository;

    @InjectMocks
    ExamServiceImp examServiceImpMock;


    @Test
    public void saveStudentMarksTest() {

        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getStudent()));
        Mockito.when(examRepository.save(Mockito.any())).thenReturn(getExam());
        examServiceImpMock.saveStudentMarks(getExamRequestDTO());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void saveStudentMarksExceptionTest() {
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        examServiceImpMock.saveStudentMarks(getExamRequestDTO());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void saveStudentMarksStudentExceptionTest() {
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        examServiceImpMock.saveStudentMarks(getExamRequestDTO());
    }


    public Exam getExam() {
        Exam exam = new Exam();
        return exam;
    }

    public Course getCourse() {
        Course course = new Course();
        course.setCourseTitle("Spring");
        return course;
    }

    public Student getStudent() {
        Student s = new Student();
        s.setStudentId(1L);
        s.setFirstName("Sam");
        List<Course> courses = new ArrayList<>();
        s.setCourses(courses);
        return s;
    }

    public ExamResquestDTO getExamRequestDTO() {
        ExamResquestDTO examResquestDTO = new ExamResquestDTO();
        examResquestDTO.setCourseId(1L);
        examResquestDTO.setStudentId(1L);
        examResquestDTO.setMaximumMarks(100);
        examResquestDTO.setObtainMarks(75);
        return examResquestDTO;
    }

}
