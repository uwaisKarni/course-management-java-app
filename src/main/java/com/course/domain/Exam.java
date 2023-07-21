package com.course.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="exam")
public class Exam {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="exam_id")
    private Long examId;
    private int obtainMarks;
    private int maximumMarks;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
