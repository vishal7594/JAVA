package com.example.quizDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizResult {
    private int correctAns;
    private  int wrongAns;
    private List<SessionQuestion> allQuestion;
    private long quizId;
}
