package com.example.quizDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubmitAnswer {
    private  long quizId;
    private  int questionId;
    private  String answer;
}
