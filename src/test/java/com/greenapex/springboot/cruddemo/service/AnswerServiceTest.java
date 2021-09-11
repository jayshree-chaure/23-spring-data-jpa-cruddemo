package com.greenapex.springboot.cruddemo.service;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.greenapex.springboot.cruddemo.dao.AnswerRepository;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Question;
import com.greenapex.springboot.cruddemo.entity.User;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = AnswerServiceTest.class)
public class AnswerServiceTest {
	
	@Mock
	AnswerRepository answerRepository;
	
	@InjectMocks
	AnswerServiceImpl answerService;
	
	@Test
	@Order(1)
	public void findAllTest() {
		List<Answer> answers = new ArrayList<Answer>(); 
		Answer answer = new Answer("b");
		answers.add(answer);
		when(answerRepository.findAll()).thenReturn(answers);
		int actual=answerService.findAll().size();
		int expected =1;
		assertEquals(expected, actual);
		
	}
	
	@Test
	@Order(2)
	public void findByIdTest() 
	{
		// mock data
		Answer answer = new Answer("b");
		answer.setAnswerId(1);
		Optional<Answer> optional = Optional.ofNullable(answer);
		
		when(answerRepository.findById(1)).thenReturn(optional);
		
		Answer actual = answerService.findById(1); 
		Answer expected = answer; 
		
		assertEquals(expected,actual);
	}
	
	@Test
	@Order(3)
	public void saveTest() {
		Answer answer = new Answer("b");
		when(answerRepository.save(answer)).thenReturn(answer);
		answerService.save(answer);
		assertEquals(answer,answerService.save(answer));
	}
	
	@Test
	@Order(4)
	public void deleteByIdTest() {
		
		answerService.deleteById(1);
		// to verify whether below method is called with required arguments
		verify(answerRepository).deleteById(1);
	}
	

}
