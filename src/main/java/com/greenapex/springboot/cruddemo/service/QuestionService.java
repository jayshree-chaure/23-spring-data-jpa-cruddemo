package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.Question;

public interface QuestionService {

	public List<Question> findAll();

	public Question findById(int theId);

	public Question save(Question theQuestion);

	public void deleteById(int theId);

}
