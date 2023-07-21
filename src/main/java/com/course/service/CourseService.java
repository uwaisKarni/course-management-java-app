package com.course.service;

import com.course.dto.request.CourseRequestDTO;
import com.course.dto.respone.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO);
    public AssignTeacherResponeDTO assignTeacher (Long courseId,Long teacherId);

    public List<CourseMarksheetResponseDTO> generateCourseMarksheet(Long courseId);
    public CourseStudentDetailsResponseDTO getCourseStudentDetails(Long id);
     public List<CourseViewResponseDTO> getCourseViewList();
}
