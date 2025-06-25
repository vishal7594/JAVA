package com.example.quizDemo.repository;

import com.example.quizDemo.model.Question;
import com.example.quizDemo.model.SessionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionQuestionRepository extends JpaRepository<SessionQuestion,Integer> {

    @Query(value =  "select count(*) from session_question where quiz_session_id = ?", nativeQuery = true)
    int getSubmittedQuestions(long quizId);

    @Query(value = "select count(*) from session_question where quiz_session_id = ? and is_correct = true",nativeQuery = true)
    int getCorrectCount(long quizId);


    @Query(value = "select count(*) from session_question where quiz_session_id = ? and is_correct = false",nativeQuery = true)
    int getWrongCount(long quizId);

    @Query(value = "select * from session_question where quiz_session_id=?",nativeQuery = true)
    List<SessionQuestion> getAllQuestions(long quizId);
}
