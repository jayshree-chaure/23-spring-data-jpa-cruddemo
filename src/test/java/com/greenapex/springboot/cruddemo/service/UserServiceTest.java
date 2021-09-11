package com.greenapex.springboot.cruddemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenapex.springboot.cruddemo.dao.UserRepository;
import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.UserServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = UserServiceTest.class)
public class UserServiceTest {
	
	//-> Controller -> service -> Repository use Entity class to get data from database 
	
	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userService;

	@Test
	@Order(1)
	public void findAllUserTest() {

		List<User> users = new ArrayList<User>();
		users.add(new User("Abc","Abc@gmail.com","abcd"));
		when(userRepository.findAll()).thenReturn(users);
		int actual = userService.findAllUser().size();
		int expected = 1;
		assertEquals(expected,actual);
	}

	@Test
	@Order(2)
	public void findByIdTest() 
	{
		// mock data
		User user = new User("xyz","xyz@gmail.com","xyzw");
		user.setUserId(1);
		Optional<User> optional = Optional.ofNullable(user);
		
		when(userRepository.findById(1)).thenReturn(optional);
		
		User actual = userService.findById(1); 
		User expected = user; 
		
		assertEquals(expected,actual);
	} 

	@Test
	@Order(3)
	public void saveUserTest() {
		User user = new User("xyz","xyz@gmail.com","xyzw");
		when(userRepository.save(user)).thenReturn(user);
		userService.saveUser(user);
		assertEquals(user,userService.saveUser(user));
	}
	
	@Test
	@Order(4)
	public void deleteByIdTest() {
		
		userService.deleteById(1);
		// to verify whether below method is called with required arguments
		verify(userRepository).deleteById(1);
	}
}
