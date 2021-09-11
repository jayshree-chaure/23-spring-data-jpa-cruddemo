package com.greenapex.springboot.cruddemo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quiz")

public class Quiz {
	@Id
	@Column(name="quiz_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int quizId;

	@Column(name="quiz_name")
	private String quizName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="quiz_id")
	private List<Question> questions;
		
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	
	public Quiz() {
		
	}

	public Quiz(String quizName, List<Question> questions) {
		this.quizName = quizName;
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizName=" + quizName + ", questions=" + questions + "]";
	}
}
