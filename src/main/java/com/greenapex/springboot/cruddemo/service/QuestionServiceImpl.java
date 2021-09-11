package com.greenapex.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greenapex.springboot.cruddemo.dao.QuestionRepository;
import com.greenapex.springboot.cruddemo.entity.Question;

@Service
public class QuestionServiceImpl implements QuestionService {
	private QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(QuestionRepository theQuestionRepository) 
	{
		questionRepository = theQuestionRepository;
	}

	public List<Question> findAll()
	{
		return questionRepository.findAll();
	}

	public Question findById(int theId) 
	{
		Optional<Question> result = questionRepository.findById(theId);

		Question theQuestion = null;
		if(result.isPresent())
			theQuestion = result.get();
		else
			throw new RuntimeException("Didn't find Question with Id "+theId);

		return theQuestion;
	}

	public Question save(Question theQuestion) 
	{
		return questionRepository.save(theQuestion);
	}

	public void deleteById(int theId)
	{
		questionRepository.deleteById(theId);
	}

}
