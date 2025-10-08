package com.f1.Formula1.dtos;

import com.f1.Formula1.entities.Comment;

public class NoticeCommentDTO extends CommentDTO{

    private Long noticeId;

	public NoticeCommentDTO(Comment comment, Long noticeId) {
		super(comment);
		this.noticeId = noticeId;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

}
