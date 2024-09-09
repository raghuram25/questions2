package com.questions2.questions2.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.questions2.questions2.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

    @Query(value= "SELECT * from get_random_questions_ids(:category, :numQ)", nativeQuery=true)
    List<Integer> findRandomQuestionsByCategoryandNumOfQuestions(String category, int numQ);

    /* @Query(value = "select * from get_wrapper_questions_by_ids(CAST(:questionIds as INT[]))", nativeQuery = true)
    List<QuestionWrapper> findWrapperQuestionsByIds(List<Integer> questionIds); */
    
}
