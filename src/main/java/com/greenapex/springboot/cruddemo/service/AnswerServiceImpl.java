package com.greenapex.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenapex.springboot.cruddemo.dao.AnswerRepository;
import com.greenapex.springboot.cruddemo.dao.AssessmentRepository;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Assessment;

@Service
public class AnswerServiceImpl implements AnswerService {
	private AnswerRepository answerRepository;

	@Autowired
	public AnswerServiceImpl(AnswerRepository theAnswerRepository) 
	{
		answerRepository = theAnswerRepository;
	}

	public List<Answer> findAll()
	{
		return answerRepository.findAll();
	}

	public Answer findById(int theAnswerId) 
	{
		Optional<Answer> result = answerRepository.findById(theAnswerId);

		Answer theAnswer = null;
		if(result.isPresent())
			theAnswer = result.get();
		else
			throw new RuntimeException("Didn't find User with Id "+theAnswerId);

		return theAnswer;
	}

	public Answer save(Answer theAnswer) 
	{
		return answerRepository.save(theAnswer);
	}

	public void deleteById(int theAnswerId)
	{
		answerRepository.deleteById(theAnswerId);
	}

}
