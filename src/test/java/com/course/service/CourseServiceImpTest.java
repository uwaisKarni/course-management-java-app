package com.course.service;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.domain.Student;
import com.course.domain.Teacher;
import com.course.dto.request.CourseRequestDTO;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import com.course.repository.TeacherRepository;
import com.course.service.imp.CourseServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImpTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    TeacherRepository teacherRepository;

    @Mock
    ExamRepository examRepository;

    @InjectMocks
    CourseServiceImp courseServiceImpMock;


    @Test
    public void addCourseTest(){

        Mockito.when(courseRepository.save(Mockito.any())).thenReturn(getCourse());
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));
        courseServiceImpMock.addCourse(getCourseRequestDTO());

    }



    @Test
    public void assignTeacherTest(){

        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));
        Mockito.when(courseRepository.save(Mockito.any())).thenReturn(getCourse());
        courseServiceImpMock.assignTeacher(1L,1L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void assignTeacherCourseExceptionTest(){
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        courseServiceImpMock.assignTeacher(1L,1L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void assignTeacherExceptionTest(){

        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        courseServiceImpMock.assignTeacher(1L,1L);

    }

    @Test
    public void generateCourseMarksheetTest(){
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        courseServiceImpMock.generateCourseMarksheet(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void generateCourseExceptionTestMarksheetTest(){
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        courseServiceImpMock.generateCourseMarksheet(1L);
    }

    @Test
    public void getCourseStudentDetails(){
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCourse()));
        courseServiceImpMock.getCourseStudentDetails(1L);
        verify(courseRepository, times(1)).findById(Mockito.anyLong());
    }


    @Test
    public void getCourseViewList(){
        Mockito.when(courseRepository.findAll()).thenReturn(Arrays.asList(getCourse()));
        courseServiceImpMock.getCourseViewList();

    }


    public Course getCourse(){
      Course course = new Course();
      course.setCourseTitle("Spring");
      course.setMaximumMarks(100);
      List<Student> students = new ArrayList<>();
      Student s= new Student();
      s.setFirstName("Ram");
      s.setLastName("Kumar");
      s.setEmail("k@gmail.com");
      s.setStudentId(1L);
     students.add(s);
      course.setStudents(students);
      return course;
    }
     public Teacher getTeacher(){
      Teacher teacher= new Teacher();
        return teacher;
     }

    public CourseRequestDTO getCourseRequestDTO(){
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
        courseRequestDTO.setTeacherId(2L);
        return courseRequestDTO;
    }

}
