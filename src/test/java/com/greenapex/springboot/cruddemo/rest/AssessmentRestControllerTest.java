package com.greenapex.springboot.cruddemo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Assessment;
import com.greenapex.springboot.cruddemo.service.AnswerService;
import com.greenapex.springboot.cruddemo.service.AssessmentService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {AssessmentRestControllerTest.class})
public class AssessmentRestControllerTest {

	@Autowired
	MockMvc mockmvc;

	@Mock
	AssessmentService assessmentService;

	@Mock
	AnswerService answerService;


	@InjectMocks
	AssessmentRestController assessmentRestController;

	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(assessmentRestController).build();
	}

	@Test
	@Order(1)
	public void  findAllTest() throws Exception {
		List<Assessment> assessments = new ArrayList<Assessment>();
		assessments.add(new Assessment(1,1,1,"b",true));

		Answer answer = new Answer("a");

		when(assessmentService.findAll()).thenReturn(assessments);

		when(answerService.findById(1)).thenReturn(answer);

		this.mockmvc.perform(get("/api/assessments"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(assessments.size()))
		.andDo(print());


	}

	@Test
	@Order(2)
	public void  findIdTest() throws Exception {
		Assessment assessment = new Assessment(1,1,1,"b",true);
		assessment.setId(1);

		Answer answer = new Answer("b");

		when(assessmentService.findById(1)).thenReturn(assessment);

		when(answerService.findById(1)).thenReturn(answer);

		this.mockmvc.perform(get("/api/assessments/1",1))
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
	public void saveAssessmentTest() throws Exception {
		Assessment assessment = new Assessment(1,1,1,"Priya",true);
		assessment.setId(0);
		Answer answer = new Answer("a");

		when(assessmentService.save(assessment)).thenReturn(assessment);

		when(answerService.findById(1)).thenReturn(answer);


		ObjectMapper mapper = new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(assessment);
		System.out.println(jsonbody);
		this.mockmvc.perform(post("/api/assessments")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andDo(print());

	}

	@Test
	@Order(4)
	public void updateAssessmentTest() throws Exception {
		Assessment assessment = new Assessment(1,1,1,"a",true);
		assessment.setId(1);

		Answer answer = new Answer("a");

		when(assessmentService.save(assessment)).thenReturn(assessment);
		when(answerService.findById(1)).thenReturn(answer);
		ObjectMapper mapper = new ObjectMapper();

		String jsonbody=mapper.writeValueAsString(assessment);

		this.mockmvc.perform(put("/api/assessments").content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.quizId").value(1))	
		.andExpect(MockMvcResultMatchers.jsonPath("$.questionId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("a"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.correct").value(true))
		.andDo(print());

	}
	@Test
	@Order(5)
	public void findQuizTest() {
		List<Assessment> assessments = new ArrayList<Assessment>();
		assessments.add(new Assessment(1,1,1,"b",true));

		Answer answer = new Answer("a");

		when(assessmentService.findAll()).thenReturn(assessments);

		when(answerService.findById(1)).thenReturn(answer);

		int actual=assessmentRestController.findQuiz().size(); 
		int expected = 1;

		verify(assessmentService).findAll();
		verify(answerService).findById(1);

		assertEquals(expected,actual);


	}


	@Test
	@Order(6)
	public void deleteAsessmentTest() throws Exception {
		Assessment assessment = new Assessment(1,1,1,"b",true);
		assessment.setId(1);

		when(assessmentService.findById(1)).thenReturn(assessment);

		assessmentRestController.deleteAssessment(1);
		this.mockmvc.perform(delete("/api/assessments/1",1))
		.andExpect(status().isOk());

	}
}
