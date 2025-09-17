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
@Table(name = "notices")
public class Notice implements Serializable, ICopyable<Notice>{

	private static final long serialVersionUID = 4463171584869937360L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	private String title;
	private String summary;
	private String text;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	public Notice() {
		super();
	}

	public Notice(Long id, Date date, String summary, String text, String title, String image, User user) {
		super();
		this.id = id;
		this.date = date;
		this.summary = summary;
		this.text = text;
		this.title = title;
		this.image = image;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public void copyProperties(Notice notice) {
		this.id = notice.getId();
		this.date = notice.getDate();
		this.summary = notice.getSummary();
		this.text = notice.getText();
		this.title = notice.getTitle();
		this.image = notice.getImage();
		this.user = notice.getUser();
		
	}
}
