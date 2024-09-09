package com.questions2.questions2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questions2.questions2.models.Question;
import com.questions2.questions2.models.QuestionWrapper;
import com.questions2.questions2.models.UserResponse;
import com.questions2.questions2.repositories.QuestionRepository;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question entity) {
        questionRepository.save(entity);
    }

    public List<Integer> generateQuestions(String category, int numQ) {
        return questionRepository.findRandomQuestionsByCategoryandNumOfQuestions(category,numQ);
    }

    public List<QuestionWrapper> getWrapperQuestions(List<Integer> questionIds) {
        List<Question> questions = questionRepository.findAll();

        List<QuestionWrapper> wrappers = new ArrayList<>();

        for (Integer id : questionIds) {
            for (Question q : questions) {
                if(q.getId()==id){
                    QuestionWrapper wrapper = new QuestionWrapper();
                    wrapper.setId(q.getId());
                    wrapper.setQuestionTitle(q.getQuestionTitle());
                    wrapper.setOption1(q.getOption1());
                    wrapper.setOption2(q.getOption2());
                    wrapper.setOption3(q.getOption3());
                    wrapper.setOption4(q.getOption4());
                    wrappers.add(wrapper);
                }  
            }
        }
        return wrappers;
    }
    //calculate score
    public ResponseEntity<Integer> submitQuiz(List<UserResponse> responses) {

       List<Question> questions = questionRepository.findAll();

       int rightAnswers = 0;
       for(UserResponse ur: responses){
            for(Question q: questions){
                if(q.getId() == ur.getId()){
                    if(ur.getResponse().equals(q.getRightAnswer())){
                        rightAnswers++;
                    }
                }
            }
       }
       return new ResponseEntity<>(rightAnswers,HttpStatus.OK);
    }
    
}
