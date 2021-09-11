package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenapex.springboot.cruddemo.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
