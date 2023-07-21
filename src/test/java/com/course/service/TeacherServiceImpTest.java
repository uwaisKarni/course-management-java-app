package com.course.service;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.domain.Teacher;
import com.course.dto.request.TeacherRequestDTO;
import com.course.mapper.TeacherMapper;
import com.course.repository.TeacherRepository;
import com.course.service.imp.TeacherServiceImp;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceImpTest {

    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherMapper teacherMapper;

    @InjectMocks
    TeacherServiceImp teacherServiceImpMock;

    @Test
    public void addStudentTest() {
       // Mockito.when(teacherMapper.teacherDtoToEntity(Mockito.any())).thenReturn(getTeacher());
        Mockito.when(teacherRepository.save(Mockito.any())).thenReturn(getTeacher());
        teacherServiceImpMock.addTeacher(getTeacherRequestDTO());
    }

    @Test
    public void updateTeacherTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));
        Mockito.when(teacherRepository.save(Mockito.any())).thenReturn(getTeacher());
        teacherServiceImpMock.updateTeacher(getTeacherRequestDTO(),1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateTeacherExceptionTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        teacherServiceImpMock.updateTeacher(getTeacherRequestDTO(),1L);

    }

    @Test
    public void deleteTeachertTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));
        teacherServiceImpMock.deletTeacher(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteTeacherExceptionTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        teacherServiceImpMock.deletTeacher(1L);
    }
    @Test
    public void getTeacherTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));
        teacherServiceImpMock.getTeacher(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getTeacherExceptionTest() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        teacherServiceImpMock.getTeacher(1L);
    }

    @Test
    public void allTeacherTest() {
        Mockito.when(teacherRepository.findAll()).thenReturn(Arrays.asList(getTeacher()));
        teacherServiceImpMock.allTeachers();
    }
    

    public Teacher getTeacher(){
     Teacher teacher = new Teacher();
     teacher.setTeacherId(1L);
     Course c =new Course();
     c.setTeacher(teacher);
     teacher.setCourses(Arrays.asList(c));
     return teacher;

    }

    public TeacherRequestDTO getTeacherRequestDTO(){
        TeacherRequestDTO teacherRequestDTO = new TeacherRequestDTO();
        return teacherRequestDTO;
    }





}
