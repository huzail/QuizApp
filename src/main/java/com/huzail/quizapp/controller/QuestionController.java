package com.huzail.quizapp.controller;

import com.huzail.quizapp.model.Question;
import com.huzail.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Below code is used for defining variable to route to the path
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    //@RequestBody is use to handle json request from client side
    @PostMapping("add")
    public ResponseEntity<String>addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping(value = "deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }

    @PostMapping("questionCategory/")
    public ResponseEntity<Object[]> countQuestionsByCategory() {
        return questionService.countQuestionsByCategory();
    }

}
