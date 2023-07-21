package com.course.controller;


import com.course.domain.Teacher;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.respone.DeleteResourseResponeDTO;
import com.course.dto.respone.UpdateResourseResponseDTO;
import com.course.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@CrossOrigin
@Slf4j
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<UpdateResourseResponseDTO> updateTeacher (@RequestBody TeacherRequestDTO teacherRequest,@PathVariable Long teacherId){
        log.info("updateTeacher method started: ");
        UpdateResourseResponseDTO response= teacherService.updateTeacher(teacherRequest,teacherId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<DeleteResourseResponeDTO> deleteTeacher (@PathVariable Long teacherId){
        log.info("deleteTeacher method started: ");
        DeleteResourseResponeDTO response= teacherService.deletTeacher(teacherId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher (@PathVariable Long id){
        log.info("getTeacher method started: ");
        Teacher response = teacherService.getTeacher(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all-teachers")
    public ResponseEntity<List<Teacher>> allTeachers (){
        log.info("allTeacher method started: ");
        List<Teacher> response = teacherService.allTeachers();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
