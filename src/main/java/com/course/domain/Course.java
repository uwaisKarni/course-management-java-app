package com.course.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="course")
public class Course {
	 

	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 @Column(name="course_id")
	 private Long courseId;
	
	 private String courseName;
	 private String courseTitle;
	 private Integer maximumMarks;

	 @ManyToMany(mappedBy = "courses",cascade = { CascadeType.PERSIST })
     private List<Student> students;
	 
	 @ManyToOne
     @JoinColumn(name = "teacher_id")
     private Teacher teacher;

	@OneToMany(mappedBy = "course")
	private List<Exam> exams = new ArrayList<>();



}
