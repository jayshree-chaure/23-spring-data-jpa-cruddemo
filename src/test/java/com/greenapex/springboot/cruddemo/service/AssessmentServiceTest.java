package com.greenapex.springboot.cruddemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greenapex.springboot.cruddemo.dao.AssessmentRepository;
import com.greenapex.springboot.cruddemo.entity.Assessment;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = AssessmentServiceTest.class)
public class AssessmentServiceTest {
	
	@Mock
	AssessmentRepository assessmentRepository;
	
	@InjectMocks
	AssessmentServiceImpl assessmentService;
		
	@Test
	@Order(1)
	public void findAllTest() {

		List<Assessment> assessment = new ArrayList<Assessment>();
		assessment.add(new Assessment(1,1,1,"Priya",true));
		when(assessmentRepository.findAll()).thenReturn(assessment);
		int actual = assessmentService.findAll().size();
		int expected = 1;
		assertEquals(expected,actual);
	}

	@Test
	@Order(2)
	public void findByIdTest() 
	{
		// mock data
		Assessment assessment = new Assessment(1,1,1,"Priya",true);
		assessment.setId(1);
		Optional<Assessment> optional = Optional.ofNullable(assessment);
		
		when(assessmentRepository.findById(1)).thenReturn(optional);
		
		Assessment actual = assessmentService.findById(1); 
		Assessment expected = assessment; 
		
		assertEquals(expected,actual);
	} 
	
	@Test
	@Order(3)
	public void saveTest() {
		Assessment assessment = new Assessment(1,1,1,"Priya",true);
		assessment.setUserId(1);
		when(assessmentRepository.save(assessment)).thenReturn(assessment);
		assertEquals(assessment,assessmentService.save(assessment));
	}
	
	@Test
	@Order(4)
	public void deleteByIdTest() {
		
		assessmentService.deleteById(1);		
		verify(assessmentRepository).deleteById(1);
	}

	
	
}
