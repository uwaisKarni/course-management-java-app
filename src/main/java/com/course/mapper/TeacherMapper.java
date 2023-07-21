package com.course.mapper;

import com.course.domain.Teacher;
import com.course.dto.request.TeacherRequestDTO;

public class TeacherMapper {

    public Teacher teacherDtoToEntity (TeacherRequestDTO teacherRequestDTO) {

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName());
        teacher.setCode(teacherRequestDTO.getCode());
        teacher.setEmail(teacherRequestDTO.getEmail());
        return teacher;

    }
}
