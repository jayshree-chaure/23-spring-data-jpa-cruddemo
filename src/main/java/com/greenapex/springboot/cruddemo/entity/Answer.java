package com.greenapex.springboot.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="answer")
public class Answer {
	@Id
	@Column(name="answer_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int answerId;

	@Column(name="answer")
	private String answer;
	
	/*@Column(name="question_id")
	private String questionId; */

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Answer() {
		
	}

	public Answer(String answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answer=" + answer + "]";
	}
}
