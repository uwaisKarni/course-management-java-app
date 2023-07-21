package com.course.mapper;

import com.course.domain.Course;
import com.course.dto.request.CourseRequestDTO;

public class CourseMapper {

    public Course courseDtoToEntity (CourseRequestDTO courseRequestDTO) {

        Course course = new Course();
        course.setCourseName(courseRequestDTO.getCourseName());
        course.setCourseTitle(courseRequestDTO.getCourseTitle());
        course.setMaximumMarks(courseRequestDTO.getMaximumMarks());
        return course;

    }
    

}
