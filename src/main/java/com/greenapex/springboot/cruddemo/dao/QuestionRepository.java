package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenapex.springboot.cruddemo.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
