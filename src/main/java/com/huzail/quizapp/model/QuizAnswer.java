package com.huzail.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    private int marks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Quiz quiz;

    public QuizAnswer(User user, Quiz quiz, int marks) {
        this.user = user;
        this.quiz = quiz;
        this.marks = marks;
    }
}
