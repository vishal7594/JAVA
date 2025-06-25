package com.example.quizDemo.service;

import com.example.quizDemo.model.*;
import com.example.quizDemo.model.reponse.Response;
import com.example.quizDemo.model.reponse.QuestionAddResponse;
import com.example.quizDemo.repository.QuestionRepository;
import com.example.quizDemo.repository.QuizRepository;
import com.example.quizDemo.repository.SessionQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SessionQuestionRepository sqRespository;

    public Response<QuizSession> startQuiz(String username) {
        Response<QuizSession> response;
          try {
              QuizSession quizSession = new QuizSession();
              quizSession.setUserName(username);
              quizRepository.save(quizSession);
              response = new Response<>(true,"", quizSession );
          }catch (Exception e)
          {
              response = new Response<>(false,"Not start successfully", null);
          }
          return  response;
    }

    public Response<QuestionAddResponse> addQuestions(List<Question> questions) {

        Response<QuestionAddResponse> response = null;
        try {
            questionRepository.saveAll(questions);
            response = new Response<>(true,"", new QuestionAddResponse(questions));
        } catch (Exception e) {
            response = new Response<>(false,"Not added successfully", null);
        }
        return  response;
    }

    public Response<Question> getQuestion(long quizId) {
        Response<Question> response = null;
        try {
            QuizSession quizSession = quizRepository.findById(quizId).orElse(null);
            if(quizSession == null) throw new Exception("Quiz is not exist with this Id.");

            int submittedQues = sqRespository.getSubmittedQuestions(quizId);

            if(submittedQues >= 5)
            {
                throw new Exception("Your quiz is over.");
            }
            Question question = questionRepository.getRandomQestion(quizId);
            response = new Response<>(true,"",question);

        } catch (Exception e) {
            response = new Response<>(false,e.getMessage(),null);
        }
        return  response;
    }

    public Response<String> submitAnswer(SubmitAnswer answer) {
        Response<String> response = null;

        try {
            QuizSession quizSession = quizRepository.findById(answer.getQuizId()).orElse(null);
            if(quizSession == null) throw new Exception("Quiz is not exist with this Id.");

            Question question = questionRepository.findById(answer.getQuestionId()).orElse(null);
            if(question == null) throw new Exception("Question is not exist with this Id.");

            int submittedQues = sqRespository.getSubmittedQuestions(answer.getQuizId());
            if(submittedQues >= 5)
            {
                throw new Exception("Your quiz is over.");
            }
            System.out.println("my answer " + answer.getAnswer());
            System.out.println("corerct answer " + question.getCorrectOption());
            boolean isCorrect = answer.getAnswer().equals(question.getCorrectOption());

            SessionQuestion sq = new SessionQuestion();
            sq.setQuizSession(quizSession);
            sq.setQuestion(question);
            sq.setCorrect(isCorrect);

            sqRespository.save(sq);

            response = new Response<>(true,"Submit Successfully",null);

        } catch (Exception e) {
            response = new Response<>(false,e.getMessage(),null);
        }

        return  response;
    }

    public Response<QuizResult> getQuizResult(long quizId) {

        Response<QuizResult> response = null;

        try {

            QuizSession quizSession = quizRepository.findById(quizId).orElse(null);
            if(quizSession == null) throw new Exception("Quiz is not exist with this Id.");

            int submittedQue = sqRespository.getSubmittedQuestions(quizId);
            if(submittedQue < 5) throw new Exception("First complete your quiz");

            int correctAns = sqRespository.getCorrectCount(quizId);
            int wrongAns = sqRespository.getWrongCount(quizId);

            List<SessionQuestion> allQuestion = sqRespository.getAllQuestions(quizId);
            QuizResult quizResult = new QuizResult(correctAns,wrongAns,allQuestion,quizId);

            response = new Response<>(true,"",quizResult);

        } catch (Exception e) {
            response =  new Response<>(false,e.getMessage(),null);
        }

        return  response;

    }

    public Response<String> deleteQuiz(long quizId) {

        Response<String> response = null;
        try {
            QuizSession quizSession = quizRepository.findById(quizId).orElse(null);
            if(quizSession == null) throw new Exception("Quiz is not exist with this Id.");

            quizRepository.deleteById(quizId);
            response =   new Response<>(true,"","Delete Success");

        } catch (Exception e) {
            response =   new Response<>(false,e.getMessage(),null);
        }

        return  response;

    }
}
























