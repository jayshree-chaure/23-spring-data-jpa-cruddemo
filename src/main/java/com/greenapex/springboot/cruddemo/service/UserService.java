package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.User;

public interface UserService {
	
	public List<User> findAllUser();
	
	public User findById(int theId);
	
	public User saveUser(User theUser);
	
	public void deleteById(int theId);
}
