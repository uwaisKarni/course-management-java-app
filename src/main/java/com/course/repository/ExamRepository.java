package com.course.repository;

import com.course.domain.Course;
import com.course.domain.Exam;
import com.course.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository  extends JpaRepository<Exam,Long> {

    @Query(value = "SELECT obtain_marks FROM exam WHERE student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    Integer getObtainMarksByStudentAndCourse(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    Exam findByStudentAndCourse(Student student,Course course);


}
