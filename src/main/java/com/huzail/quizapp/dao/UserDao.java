package com.huzail.quizapp.dao;

import com.huzail.quizapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserDao extends JpaRepository<User, Integer>{
    User findUsersByUsername(String username);
}
