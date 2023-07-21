package com.course.service.imp;


import com.course.Exception.ResourceNotFoundException;
import com.course.domain.Course;
import com.course.domain.Exam;
import com.course.domain.Student;
import com.course.dto.request.ExamResquestDTO;
import com.course.dto.respone.SaveMarksResponeDTO;
import com.course.repository.CourseRepository;
import com.course.repository.ExamRepository;
import com.course.repository.StudentRepository;
import com.course.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ExamServiceImp implements ExamService {


    @Autowired
    ExamRepository examRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;


    @Override
    public SaveMarksResponeDTO saveStudentMarks(ExamResquestDTO examResquestDTO) {

        SaveMarksResponeDTO dto =new SaveMarksResponeDTO();
        Optional<Course> course = courseRepository.findById(examResquestDTO.getCourseId());

        if(!course.isPresent()){
            throw new ResourceNotFoundException("Course","id",examResquestDTO.getCourseId());
        }
        Optional<Student> student = studentRepository.findById(examResquestDTO.getStudentId());
        if(!student.isPresent()){
            throw new ResourceNotFoundException("Student","id",examResquestDTO.getStudentId());
        }

        Exam isMarksExist = examRepository.findByStudentAndCourse(student.get(),course.get());
        if(isMarksExist !=null){
            isMarksExist.setMaximumMarks(examResquestDTO.getMaximumMarks());
            isMarksExist.setObtainMarks(examResquestDTO.getObtainMarks());
            examRepository.save(isMarksExist);
            dto.setMsg("Marks Updated Successfully");
            return dto;
        }

        Exam e= new Exam();
        e.setStudent(student.get());
        e.setCourse(course.get());
        e.setObtainMarks(examResquestDTO.getObtainMarks());
        e.setMaximumMarks(examResquestDTO.getMaximumMarks());
        examRepository.save(e);
        dto.setMsg("Marks Assigned Successfully");
        return dto;
    }
}
