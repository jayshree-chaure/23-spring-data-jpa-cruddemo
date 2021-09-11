package com.greenapex.springboot.cruddemo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greenapex.springboot.cruddemo.entity.Userquiz;
import com.greenapex.springboot.cruddemo.service.UserquizService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {UserquizRestControllerTest.class})
public class UserquizRestControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	@Mock
	UserquizService userquizService;

	@InjectMocks
	UserquizRestController userquizRestController;
	
	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(userquizRestController).build();
	}

	

	@Test
	@Order(1)
	public void  findAllUserQuizTest() throws Exception {
		ArrayList<Userquiz> userQuiz = new ArrayList<Userquiz> ();
		userQuiz.add(new Userquiz(1,1,1,"b",true));

		when(userquizService.findAll()).thenReturn(userQuiz);
		this.mockmvc.perform(get("/api/userQuizes"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(userQuiz.size()))
		.andDo(print());


	}

	@Test
	@Order(2)
	public void  getUserQuizTest() throws Exception {
		Userquiz userquiz = new Userquiz(1,1,1,"b",true);
		userquiz.setId(1);


		when(userquizService.findById(1)).thenReturn(userquiz);

		this.mockmvc.perform(get("/api/userQuizes/1",1))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.quizId").value(1))	
		.andExpect(MockMvcResultMatchers.jsonPath("$.questionId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("b"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.correct").value(true))
		.andDo(print());

	}

	
	@Test
	@Order(3)
	public void deleteUserQuizTest() {
		Userquiz userquiz = new Userquiz(1,1,1,"Priya",true);
		userquiz.setUserId(1);

		when(userquizService.findById(1)).thenReturn(userquiz);

		userquizRestController.deleteUserquiz(1);
		verify(userquizService).findById(1);
		verify(userquizService).deleteById(1);
	}

}
