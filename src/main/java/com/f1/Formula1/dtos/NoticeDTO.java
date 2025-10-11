package com.f1.Formula1.dtos;

import java.util.Date;

import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.entities.User;

public class NoticeDTO {

	private Long id;
	private Date date;
	private String title;
	private String summary;
	private String text;
	private String image;
	private User user;
    private int commentCount;
	
    public NoticeDTO() {
		super();
	}
	
    public NoticeDTO(Notice notice) {
		super();
		this.id = notice.getId();
		this.date = notice.getDate();
		this.title = notice.getTitle();
		this.summary = notice.getSummary();
		this.text = notice.getSummary();
		this.image = notice.getImage();
		this.user = notice.getUser();
		this.commentCount = notice.getComments().size();
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
