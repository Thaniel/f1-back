package com.f1.Formula1.dtos;

import java.util.Date;

import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.entities.User;

public class TopicDTO {
    private Long id;
    private String title;
    private Date date;
    private User user;
    private int commentCount;
	
    public TopicDTO() {
		super();
	}
      
    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.date = topic.getDate();
        this.title = topic.getTitle();
        this.user = topic.getUser();
        this.commentCount = topic.getComments().size();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
