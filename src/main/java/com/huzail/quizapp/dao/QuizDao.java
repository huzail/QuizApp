package com.huzail.quizapp.dao;

import com.huzail.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
