package com.example.quizDemo.repository;

import com.example.quizDemo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Integer> {

    @Query(value = "SELECT * FROM question where id  not in " +
            "(select question_id from session_question where quiz_session_id = ?)  " +
            "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question getRandomQestion(long quizId);

}
