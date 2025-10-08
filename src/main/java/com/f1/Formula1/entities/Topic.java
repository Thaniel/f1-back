package com.f1.Formula1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic implements Serializable, ICopyable<Topic> {

	private static final long serialVersionUID = 6794602347131323709L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date date;
	private String title;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TopicComment> comments = new ArrayList<>();

	public Topic() {
		super();
	}

	public Topic(Long id, Date date, String title, User user) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<TopicComment> getComments() {
		return comments;
	}

	public void setComments(List<TopicComment> comments) {
		this.comments = comments;
	}

	@Override
	public void copyProperties(Topic topic) {
		this.id = topic.getId();
		this.date = topic.getDate();
		this.title = topic.getTitle();
		this.user = topic.getUser();
		
	}

}
