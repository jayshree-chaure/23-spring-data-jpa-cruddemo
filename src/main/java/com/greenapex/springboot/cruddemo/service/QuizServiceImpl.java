package com.greenapex.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenapex.springboot.cruddemo.dao.QuizRepository;
import com.greenapex.springboot.cruddemo.entity.Quiz;
import com.greenapex.springboot.cruddemo.entity.User;

@Service
public class QuizServiceImpl implements QuizService {

	private QuizRepository quizRepository;

	@Autowired
	public QuizServiceImpl(QuizRepository thequizRepository) 
	{
		quizRepository = thequizRepository;
	}

	@Override
	public List<Quiz> findAll() {
		return quizRepository.findAll();
	}

	@Override
	public Quiz findById(int theId) {
		Optional<Quiz> result = quizRepository.findById(theId);

		Quiz theUser = null;
		if(result.isPresent())
			theUser = result.get();
		else
			throw new RuntimeException("Didn't find User with Id "+theId);

		return theUser;
	}

	@Override
	public Quiz save(Quiz theQuiz) {
		// TODO Auto-generated method stub
		return quizRepository.save(theQuiz);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		quizRepository.deleteById(theId);

	}


}
