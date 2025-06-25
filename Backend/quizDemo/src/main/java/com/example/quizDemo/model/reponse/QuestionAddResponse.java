package com.example.quizDemo.model.reponse;

import com.example.quizDemo.model.Question;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class QuestionAddResponse {
    private List<Question> questions;
}
