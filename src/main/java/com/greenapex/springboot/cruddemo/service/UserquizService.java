package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.Userquiz;

public interface UserquizService {
	
	public List<Userquiz> findAll();
	
	public Userquiz findById(int theId);
	
	public Userquiz save(Userquiz theUser);
	
	public void deleteById(int theId);
}
