package com.f1.Formula1.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 1 table for types of comments ("NOTICE", "TOPIC")
@DiscriminatorColumn(name = "comment_type", discriminatorType = DiscriminatorType.STRING)
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

	public Comment() {
		super();
	}

	public Comment(Long id, Date date, String text, User user) {
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

	@Override
	public void copyProperties(Comment comment) {
		this.id = comment.getId();
		this.date = comment.getDate();
		this.text = comment.getText();
		this.user = comment.getUser();
	}
}
