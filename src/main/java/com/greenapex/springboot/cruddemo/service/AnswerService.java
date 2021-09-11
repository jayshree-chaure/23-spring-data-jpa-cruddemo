package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.Answer;

public interface AnswerService {
	
	public List<Answer> findAll();

	public Answer findById(int theId);

	public Answer save(Answer theAnswer);

	public void deleteById(int theId);

}
