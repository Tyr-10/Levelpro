package com.levelpro.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private int score;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<QuestionOption> options;
}
