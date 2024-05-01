package com.huzail.quizapp.service;

import com.huzail.quizapp.dao.UserDao;
import com.huzail.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public ResponseEntity<String> RegisterUser(User user){
        String username = user.getUsername();
        String availableUser = userDao.findByUsername(username);
        if(username.equals(availableUser)){
            return new ResponseEntity<>("Username already Exist", HttpStatus.CONFLICT);
        }
        else if(user.getPassword().length() < 6){
            return new ResponseEntity<>("Password should be greater than 5 digit", HttpStatus.CONFLICT);
        }
        userDao.save(user);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
