package com.huzail.quizapp.controller;

import com.huzail.quizapp.model.Question;
import com.huzail.quizapp.model.QuestionWrapper;
import com.huzail.quizapp.model.Quiz;
import com.huzail.quizapp.model.Response;
import com.huzail.quizapp.service.QuizService;
import com.huzail.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title, @RequestParam String username) {

        return quizService.createQuiz(category, numQ, title, username);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id, @RequestParam String username) {
        // Wrapper
        // Use to send specific values not the all values oof table
        return quizService.getQuizQuestion(id, username);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses, @RequestParam String username){
        return quizService.calculateResult(id, responses, username);
    }

    @GetMapping("allQuiz")
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return quizService.getAllQuiz();
    }

//    @GetMapping("allQuizScore")
//    public ResponseEntity<List<Quiz>> getAllQuizScore() {
//        return quizService.getAllQuizScore();
//    }
}
