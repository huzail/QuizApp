package com.huzail.quizapp.dao;

import com.huzail.quizapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer>{
    String findByUsername(String username);
}
