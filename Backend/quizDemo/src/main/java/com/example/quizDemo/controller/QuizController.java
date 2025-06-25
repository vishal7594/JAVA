package com.example.quizDemo.controller;

import com.example.quizDemo.model.QuizResult;
import com.example.quizDemo.model.SubmitAnswer;
import com.example.quizDemo.model.Question;
import com.example.quizDemo.model.QuizSession;
import com.example.quizDemo.model.reponse.Response;
import com.example.quizDemo.model.reponse.QuestionAddResponse;
import com.example.quizDemo.service.QuizService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/quiz-start/{username}")
    public Response<QuizSession> startQuiz(@PathVariable String username) {
        System.out.println("username " + username);
        return quizService.startQuiz(username);
    }

    @PostMapping("/qestions-add")
    public Response<QuestionAddResponse> addQuestions(@RequestBody List<Question> questions) {
        return quizService.addQuestions(questions);
    }

    @GetMapping("/get-question")
    public  Response<Question> getQuestion(@RequestParam long quizId) {
        return quizService.getQuestion(quizId);
    }

    @PostMapping("/submit-answer")
    public  Response<String> submitAnswer(@RequestBody SubmitAnswer answer)
    {
        Gson gson = new Gson();
        String json = gson.toJson(answer);
        System.out.println("check " + json);
        return quizService.submitAnswer(answer);
    }

    @GetMapping("/get-quiz-result/{quizId}")
    public  Response<QuizResult> getQuizResult(@PathVariable long quizId)
    {
         return  quizService.getQuizResult(quizId);
    }

    @DeleteMapping("/quiz-delete/{quizId}")
    public  Response<String> deleteQuiz(@PathVariable long quizId)
    {
        return  quizService.deleteQuiz(quizId);
    }

}




















