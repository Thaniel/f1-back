package com.f1.Formula1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TOPIC")
public class TopicComment extends Comment {

	private static final long serialVersionUID = -3505238599381502274L;

	@ManyToOne
	@JoinColumn(name = "id_topic", nullable = true)
	@JsonIgnore
	private Topic topic;

	public TopicComment() {
		super();
	}

	public TopicComment(Topic topic) {
		super();
		this.topic = topic;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
