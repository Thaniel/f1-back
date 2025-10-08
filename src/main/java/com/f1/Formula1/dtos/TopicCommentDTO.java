package com.f1.Formula1.dtos;

import com.f1.Formula1.entities.Comment;

public class TopicCommentDTO extends CommentDTO{
	
	private Long topicId;

	public TopicCommentDTO(Comment comment, Long topicId) {
		super(comment);
		this.topicId = topicId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
		
}
