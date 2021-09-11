package com.greenapex.springboot.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userquiz")
public class Userquiz {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="quiz_id")
	private int quizId;
	
	@Column(name="question_id")
	private int questionId;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="is_correct")
	private boolean isCorrect;
	
	public Userquiz() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Userquiz(int userId, int quizId, int questionId, String answer, boolean isCorrect) {
		this.userId = userId;
		this.quizId = quizId;
		this.questionId = questionId;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Userquiz [id=" + id + ", userId=" + userId + ", quizId=" + quizId + ", questionId=" + questionId
				+ ", answer=" + answer + ", isCorrect=" + isCorrect + "]";
	}

	
}