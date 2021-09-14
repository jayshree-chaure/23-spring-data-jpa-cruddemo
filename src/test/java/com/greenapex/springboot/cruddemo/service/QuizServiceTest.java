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

import com.greenapex.springboot.cruddemo.dao.QuizRepository;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Question;
import com.greenapex.springboot.cruddemo.entity.Quiz;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = QuizServiceTest.class)
public class QuizServiceTest {

	@Mock
	QuizRepository quizRepository;

	@InjectMocks
	QuizServiceImpl quizService;

	@Test
	@Order(1)
	public void findAllTest() {
		
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("why",new Answer("answer"));
     	que.add(question);
		List<Quiz> quiz = new ArrayList<Quiz>();
		quiz.add(new Quiz("quiz1",que));
		when(quizRepository.findAll()).thenReturn(quiz);
		int actual = quizService.findAll().size();
		int expected = 1;
		assertEquals(expected,actual);
	}

	@Test
	@Order(2)
	public void findByIdTest() 
	{
		// mock data
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("b"));
		que.add(question);
		Quiz quiz = new Quiz("Java", que);
		quiz.setQuizId(1);
		Optional<Quiz> optional = Optional.ofNullable(quiz);

		when(quizRepository.findById(1)).thenReturn(optional);

		Quiz actual = quizService.findById(1); 
		Quiz expected = quiz; 

		assertEquals(expected,actual);
	} 
	@Test
	@Order(3)
	public void saveTest() {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("c"));
     	que.add(question);
		Quiz quiz = new Quiz("Java", que);
		
		quiz.setQuizId(1);
		when(quizRepository.save(quiz)).thenReturn(quiz);
		quizService.save(quiz);
		assertEquals(quiz,quizService.save(quiz));
	}


	@Test
	@Order(4)
	public void deleteByIdTest() {

		quizService.deleteById(1);
		// to verify whether below method is called with required arguments
		verify(quizRepository).deleteById(1);
	}


}
