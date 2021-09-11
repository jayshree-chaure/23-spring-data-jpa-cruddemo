package com.greenapex.springboot.cruddemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="question")
public class Question {
	
	@Id
	@Column(name="question_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int questionId;

	@Column(name="question")
	private String question;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="question_id")
	private Answer answer;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question(String question, Answer answer) {
		this.question = question;
		this.answer = answer;
	}
	
	// Question question = new Question("gddguh",new Answer("answer"));

	public Question()
	{
		
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", question=" + question + ", answer=" + answer + "]";
	}	
}
