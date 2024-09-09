package com.questions2.questions2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questions2.questions2.models.Question;
import com.questions2.questions2.models.QuestionWrapper;
import com.questions2.questions2.models.UserResponse;
import com.questions2.questions2.services.QuestionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("/Question")
    public ResponseEntity<String> postMethodName(@RequestBody Question entity) {
        try {
            questionService.addQuestion(entity);
            return ResponseEntity.ok("Question added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
        
    }

    //take subject, numQ andgenerate questions return List<Integers> 
    @GetMapping("/generate")
    public List<Integer> generateQuestions(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestions(category, numQ);
    }


    //return QuestionWrapper Questions to UI GET by quizId
    @PostMapping("/WrapperQuestions")
    public List<QuestionWrapper> getQuizQuestions(@RequestBody List<Integer> questionIds){
        return questionService.getWrapperQuestions(questionIds);
    }

    @PostMapping("/submitQuiz")
    public ResponseEntity<Integer> getScoreOnSubmit(@RequestBody List<UserResponse> responses){
        return questionService.submitQuiz(responses);
    }

}


