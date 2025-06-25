package com.example.quizDemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuizSession {
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long quizId;
    private  String userName;

    @OneToMany(mappedBy = "quizSession",cascade = CascadeType.ALL)
    private List<SessionQuestion> sessionQuestionsList;

}
