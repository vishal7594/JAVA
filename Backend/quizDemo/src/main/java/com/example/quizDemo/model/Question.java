package com.example.quizDemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private  String questionText;
    private  String optionA;
    private  String optionB;
    private  String optionC;
    private  String optionD;
    private  String correctOption;
}
