package com.huzail.quizapp.service;

import com.huzail.quizapp.dao.QuestionDao;
import com.huzail.quizapp.dao.QuizAnswerDao;
import com.huzail.quizapp.dao.QuizDao;
import com.huzail.quizapp.dao.UserDao;
import com.huzail.quizapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao quizQuestionDao;
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private QuizAnswerDao quizAnswerDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title, String username) {

        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

        User user = userDao.findUsersByUsername(username);
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setUserId(user.getUserid());
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id, String username) {
        User user = userDao.findUsersByUsername(username);
        if(user == null){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();

        for(Question question : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionForUsers.add(qw);
        }

        return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses, String username) {
        Quiz quiz = quizDao.findById(id).get();

        User user = userDao.findUsersByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(quiz.getUserId() != user.getUserid()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response : responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        QuizAnswer quizAnswer = new QuizAnswer(user, quiz, right);
//        quizAnswer.setUser(user.getUserid());
//        quizAnswer.setMarks(right);
//        quizAnswer.setQuiz(quiz.getId());

        quizAnswerDao.save(quizAnswer);

        if(right < i / 2){
            return new ResponseEntity<>(right, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {
        try {
            return new ResponseEntity<>(quizDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }
}
