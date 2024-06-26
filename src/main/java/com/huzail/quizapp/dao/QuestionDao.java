package com.huzail.quizapp.dao;

import com.huzail.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository Pattern
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT COUNT(*) AS QUESTION_COUNT, q.CATEGORY FROM QUESTION q GROUP BY q.CATEGORY", nativeQuery = true)
    List<Object[]> countQuestionByCategory();

    @Query(value = "SELECT * FROM QUESTION q Where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
