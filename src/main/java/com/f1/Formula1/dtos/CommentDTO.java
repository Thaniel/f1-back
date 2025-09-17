package com.f1.Formula1.dtos;

import java.util.Date;

import com.f1.Formula1.entities.User;

public class CommentDTO {
	
	private Long id;
	private Date date;
	private String text;
	private User user;
    private Long noticeId;
    
	public CommentDTO() {
		super();
	}
    
	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public CommentDTO(Long id, Date date, String text, User user, Long noticeId) {
		super();
		this.id = id;
		this.date = date;
		this.text = text;
		this.user = user;
		this.noticeId = noticeId;
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
