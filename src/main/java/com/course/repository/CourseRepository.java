package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository <Course,Long>{

}
