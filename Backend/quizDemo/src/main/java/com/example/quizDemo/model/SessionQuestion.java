package com.example.quizDemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class SessionQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "quiz_session_id")
    private QuizSession quizSession;

    @ManyToOne
    @JoinColumn(name = "question_id") // Foreign key for a single question
    private Question question;

    private  boolean isCorrect;
}
