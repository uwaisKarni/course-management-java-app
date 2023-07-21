package com.course.service.imp;

import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Teacher;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.respone.DeleteResourseResponeDTO;
import com.course.dto.respone.TeacherResponseDTO;
import com.course.dto.respone.UpdateResourseResponseDTO;
import com.course.mapper.TeacherMapper;
import com.course.repository.TeacherRepository;
import com.course.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TeacherServiceImp implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {

        TeacherMapper teacherMapper = new TeacherMapper();
        Teacher teacher = teacherMapper.teacherDtoToEntity(teacherRequestDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        log.info("teacher is saved with id :-"+savedTeacher.getTeacherId());
        TeacherResponseDTO response = new TeacherResponseDTO();
        response.setId(savedTeacher.getTeacherId());
        response.setMsg("Teacher has been added");
        return response;
    }

    @Override
    public UpdateResourseResponseDTO updateTeacher(TeacherRequestDTO request,Long teacherId) {

        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(!teacher.isPresent()){
            throw new ResourceNotFoundException("Teacher","id",teacherId);
        }
        Teacher t = teacher.get();
        t.setFirstName(request.getFirstName());
        t.setLastName(request.getLastName());
        t.setEmail(request.getEmail());
        t.setCode(request.getCode());
        teacherRepository.save(t);
        UpdateResourseResponseDTO dto = new UpdateResourseResponseDTO();
        dto.setMsg("Teacher updated Successfully!");
        return dto;
    }

    @Override
    public DeleteResourseResponeDTO deletTeacher(Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(!teacher.isPresent()){
            throw new ResourceNotFoundException("Teacher","id",teacherId);
        }
        Teacher t = teacher.get();
        t.getCourses().stream().forEach(c->c.setTeacher(null));
        teacherRepository.delete(t);
        DeleteResourseResponeDTO dto = new DeleteResourseResponeDTO();
        dto.setMsg("Teacher delete Successfully !");
        return dto;
    }

    @Override
    public List<Teacher> allTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    @Override
    public Teacher getTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(!teacher.isPresent()){
            throw new ResourceNotFoundException("Teacher","id",id);
        }
        return teacher.get();

    }
}
