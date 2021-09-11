package com.greenapex.springboot.cruddemo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenapex.springboot.cruddemo.dao.UserRepository;
import com.greenapex.springboot.cruddemo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) 
	{
		userRepository = theUserRepository;
	}

	public List<User> findAllUser()
	{
		return userRepository.findAll();
	}

	public User findById(int theId) 
	{
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		if(result.isPresent())
			theUser = result.get();
		else
			throw new RuntimeException("Didn't find User with Id "+theId);
		
		return theUser;
	}

	public User saveUser(User theUser) 
	{
		return userRepository.save(theUser);
		 
	}

	public void deleteById(int theId)
	{
		userRepository.deleteById(theId);
	}
}