package com.course.service;

import com.course.dto.request.ExamResquestDTO;
import com.course.dto.respone.SaveMarksResponeDTO;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {

    public SaveMarksResponeDTO saveStudentMarks(ExamResquestDTO examResquestDTO);

}
