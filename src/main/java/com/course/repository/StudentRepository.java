package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.domain.Student;


@Repository
public interface StudentRepository extends JpaRepository <Student,Long>{
	
	
}
