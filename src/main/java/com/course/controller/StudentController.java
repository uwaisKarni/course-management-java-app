package com.course.controller;

import com.course.domain.Student;
import com.course.dto.request.StudentRequestDTO;
import com.course.dto.respone.AllStudentMarksheetResponseDTO;
import com.course.dto.respone.DeleteResourseResponeDTO;
import com.course.dto.respone.EnrollCourseResponeDTO;
import com.course.dto.respone.UpdateResourseResponseDTO;
import com.course.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PutMapping("/update/{studentId}")
    public ResponseEntity<UpdateResourseResponseDTO> updateStudent (@RequestBody StudentRequestDTO studentrequest,@PathVariable Long studentId){
        log.info("updateStudent method started: ");
        UpdateResourseResponseDTO response= studentService.updateStudent(studentrequest,studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<DeleteResourseResponeDTO> deleteStudent (@PathVariable Long studentId){
        log.info("deleteStudent method started: ");
        DeleteResourseResponeDTO response= studentService.deletStudent(studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent (@PathVariable Long id){
        log.info("allStudent method started: ");
        Student response = studentService.getStudent(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> allStudent (){
        log.info("allStudent method started: ");
        List<Student> response = studentService.allStudents();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<EnrollCourseResponeDTO> enrollCourse(@RequestParam Long courseId,@RequestParam Long studentId){
        EnrollCourseResponeDTO response= studentService.enrollCourse(courseId,studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all-students-marksheet")
    public ResponseEntity<List<AllStudentMarksheetResponseDTO>> allStudentsMarksheet(){
        List<AllStudentMarksheetResponseDTO> response = studentService.generateAllStudentsMarksheet();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }




}
