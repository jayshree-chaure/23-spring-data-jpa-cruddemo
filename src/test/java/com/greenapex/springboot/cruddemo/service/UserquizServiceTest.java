package com.greenapex.springboot.cruddemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenapex.springboot.cruddemo.dao.UserquizRepository;
import com.greenapex.springboot.cruddemo.entity.Assessment;
import com.greenapex.springboot.cruddemo.entity.Userquiz;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes=UserquizServiceTest.class)
public class UserquizServiceTest {
	
	@Mock
	UserquizRepository userQuizRepository;
	
	@InjectMocks
	UserquizServiceImpl userQuizService;
	
	@Test
	@Order(1)
	public void findAllTest() {
		ArrayList<Userquiz> userQuiz = new ArrayList<Userquiz> ();
		userQuiz.add(new Userquiz(1,1,1,"b",true));
		when(userQuizRepository.findAll()).thenReturn(userQuiz);
		int actual = userQuizService.findAll().size();
		int expected = 1;
		assertEquals(expected, actual);
		
	}
	@Test
	@Order(2)
	public void findByIdTest() {
		Userquiz userquiz = new Userquiz(1,1,1,"Abc",true);
		userquiz.setId(1);
		
		Optional <Userquiz> optional = Optional.ofNullable(userquiz);
		when(userQuizRepository.findById(1)).thenReturn(optional);
		Userquiz actual = userQuizService.findById(1);
		Userquiz expected = userquiz;
		assertEquals(expected, actual);
			
	}
	@Test
	@Order(3)
	public void saveTest() {
		Userquiz userquiz = new Userquiz(1,1,1,"Abc",true);
		userquiz.setId(1);
		
		when(userQuizRepository.save(userquiz)).thenReturn(userquiz);
		assertEquals(userquiz,userQuizService.save(userquiz));

	}
	
	@Test
	@Order(4)
	public void deleteByIdTest() {
		userQuizService.deleteById(1);
		verify(userQuizRepository).deleteById(1);
	}
	
	

}
