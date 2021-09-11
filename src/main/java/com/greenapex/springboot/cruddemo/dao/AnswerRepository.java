package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenapex.springboot.cruddemo.entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {


	 

}
