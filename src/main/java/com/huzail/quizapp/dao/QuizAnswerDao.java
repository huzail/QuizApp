package com.huzail.quizapp.dao;

import com.huzail.quizapp.model.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAnswerDao extends JpaRepository<QuizAnswer, Integer> {
}
