package com.greenapex.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenapex.springboot.cruddemo.entity.Userquiz;
import com.greenapex.springboot.cruddemo.service.UserquizService;

@RestController
@RequestMapping("/api")
public class UserquizRestController {
	
	private UserquizService userquizService;
	
	public UserquizRestController(UserquizService userquizService) 
	{
	   this.userquizService = userquizService;
	}
	
	@GetMapping("/userQuizes")
	public List<Userquiz> findAll()
	{
		return userquizService.findAll();
	}
	
	@GetMapping("userQuizes/{id}")
	public Userquiz getUserquiz(@PathVariable int id)
	{
		Userquiz theUser = userquizService.findById(id);
		
		
		if(theUser == null)
			throw new RuntimeException("User Id is not found "+ id);
		
		return theUser;
	}
	
	
	@DeleteMapping("/userQuizes/{id}")
	public String deleteUserquiz(@PathVariable int userId)
	{
		Userquiz theUser = userquizService.findById(userId);
		
		if(theUser == null)
			return "User not found with ID " +userId;
		
		userquizService.deleteById(userId);
		
		return "UserQuiz is deleted with Id "+ userId;	
	}
}
