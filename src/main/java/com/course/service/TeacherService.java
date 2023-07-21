package com.course.service;

import com.course.domain.Teacher;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.respone.DeleteResourseResponeDTO;
import com.course.dto.respone.TeacherResponseDTO;
import com.course.dto.respone.UpdateResourseResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO);
    public UpdateResourseResponseDTO updateTeacher(TeacherRequestDTO teacherRequestDTO,Long teacherId);
    public DeleteResourseResponeDTO deletTeacher(Long teacherId);

    public List<Teacher> allTeachers();

    public Teacher getTeacher(Long id);

}
