package com.example.quizDemo.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response<T> {
    private  boolean status;
    private  String message;
    private  T data;
}
