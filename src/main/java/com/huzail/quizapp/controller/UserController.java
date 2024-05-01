package com.huzail.quizapp.controller;

import com.huzail.quizapp.model.User;
import com.huzail.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.RegisterUser(user);
    }
}
