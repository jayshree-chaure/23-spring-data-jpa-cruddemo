package com.greenapex.springboot.cruddemo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenapex.springboot.cruddemo.dao.UserquizRepository;
import com.greenapex.springboot.cruddemo.entity.Userquiz;

@Service
public class UserquizServiceImpl implements UserquizService {
	
	private UserquizRepository userquizRepository;
	
	@Autowired
	public UserquizServiceImpl(UserquizRepository userquizRepository) 
	{
		this.userquizRepository = userquizRepository;
	}

	public List<Userquiz> findAll()
	{
		return userquizRepository.findAll();
	}

	public Userquiz findById(int theId) 
	{
		Optional<Userquiz> result = userquizRepository.findById(theId);
		
		Userquiz theUser = null;
		if(result.isPresent())
			theUser = result.get();
		else
			throw new RuntimeException("Didn't find UserQuiz with Id "+theId);
		
		return theUser;
	}

	public Userquiz save(Userquiz theUser) 
	{
		return userquizRepository.save(theUser);
	}

	public void deleteById(int theId)
	{
		userquizRepository.deleteById(theId);
	}
}
