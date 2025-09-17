package com.f1.Formula1.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment implements Serializable, ICopyable<Comment> {

	private static final long serialVersionUID = -6279979307442017684L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@ManyToOne
    @JoinColumn(name = "id_notice")
	@JsonIgnore
    private Notice notice;
	

	public Comment() {
		super();
	}

	public Comment(Long id, Date date, String text, User user, Notice notice) {
		super();
		this.id = id;
		this.date = date;
		this.text = text;
		this.user = user;
		this.notice = notice;
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

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	@Override
	public void copyProperties(Comment comment) {
		this.id = comment.getId();
		this.date = comment.getDate();
		this.text = comment.getText();
		this.user = comment.getUser();
		this.notice = comment.getNotice();		
	}
}
