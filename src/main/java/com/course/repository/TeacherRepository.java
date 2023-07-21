package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository <Teacher,Long>{

}
