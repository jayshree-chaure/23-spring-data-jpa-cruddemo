package com.greenapex.springboot.cruddemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.greenapex.springboot.cruddemo.dao.QuestionRepository;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Question;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = QuestionServiceTest.class)
public class QuestionServiceTest {

	@Mock
	QuestionRepository questionRepository;

	@InjectMocks
	QuestionServiceImpl questionService;

	@Test
	@Order(1)
	public void findAllTest() {
		List<Question> questions = new ArrayList<Question>(); 
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				,new Answer("c"));

		questions.add(question);

		when(questionRepository.findAll()).thenReturn(questions);
		int actual=questionService.findAll().size();
		int expected =1;
		assertEquals(expected, actual);

	}

	@Test
	@Order(2)
	public void findByIdTest() 
	{
		// mock data
		Question question =
        new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
		, new Answer ("c"));
		question.setQuestionId(1);
		Optional<Question> optional = Optional.ofNullable(question);

		when(questionRepository.findById(1)).thenReturn(optional);

		Question actual = questionService.findById(1); 
		Question expected = question; 

		assertEquals(expected,actual);
	} 

	@Test
	@Order(3)
	public void saveTest() {
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer ("c"));
		question.setQuestionId(1);
		when(questionRepository.save(question)).thenReturn(question);
		assertEquals(question,questionService.save(question));
	}
	@Test
	@Order(4)
	public void deleteByIdTest() {

		questionService.deleteById(1);
		// to verify whether below method is called with required arguments
		verify(questionRepository).deleteById(1);
	}


}
