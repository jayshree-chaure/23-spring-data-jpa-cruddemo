package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.Quiz;

public interface QuizService {

	public List<Quiz> findAll();

	public Quiz findById(int theId);

	public Quiz save(Quiz theQuiz);

	public void deleteById(int theId);

}
