package com.course.domain;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="teacher")
public class Teacher {
	
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   @Column(name="teacher_id")
	private Long teacherId;
	private String firstName;
	private String lastName;
	private String code;
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;

}
