package com.f1.Formula1.dtos;

import java.util.Date;

import com.f1.Formula1.entities.Comment;
import com.f1.Formula1.entities.User;

public class CommentDTO {

	private Long id;
	private Date date;
	private String text;
	private User user;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.date = comment.getDate();
		this.text = comment.getText();
		this.user = comment.getUser();
	}

	public CommentDTO(Long id, Date date, String text, User user) {
		super();
		this.id = id;
		this.date = date;
		this.text = text;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
