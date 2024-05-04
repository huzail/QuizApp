package com.huzail.quizapp.service;

import com.huzail.quizapp.dao.UserDao;
import com.huzail.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public ResponseEntity<String> RegisterUser(User user){
        String username = user.getUsername();
        User availableUser = userDao.findUsersByUsername(username);
        if(availableUser != null){
            return new ResponseEntity<>("Username exist", HttpStatus.CONFLICT);
        }
        if(user.getPassword().length() < 6){
            return new ResponseEntity<>("Password should be greater than 5 digit", HttpStatus.CONFLICT);
        }
        userDao.save(user);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> Login(String username, String password) {
        User user = userDao.findUsersByUsername(username);
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        else if(user.getPassword().equals(password)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Password does not match", HttpStatus.CONFLICT);
    }
}
