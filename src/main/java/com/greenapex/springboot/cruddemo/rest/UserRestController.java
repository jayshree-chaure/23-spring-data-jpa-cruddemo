package com.greenapex.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserService userService;
	
	public UserRestController(UserService userService) 
	{
	   this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<User> findAllUser()
	{
		return userService.findAllUser();
	}
	
	@GetMapping("users/{userId}")
	public User getUser(@PathVariable int userId)
	{
		User theUser = userService.findById(userId);
		
		
		if(theUser == null)
			throw new RuntimeException("User Id is not found "+ userId);
		
		return theUser;
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User theUser)
	{
		theUser.setUserId(0);
		userService.saveUser(theUser);
		
		return theUser;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User theUser)
	{
		userService.saveUser(theUser);

		return theUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId)
	{
		User theUser = userService.findById(userId);
		
		if(theUser == null)
			return "User not found with ID " +userId;
		
		userService.deleteById(userId);
		
		return "User is deleted with Id "+ userId;	
	}
}
