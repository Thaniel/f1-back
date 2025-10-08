package com.f1.Formula1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("NOTICE")
public class NoticeComment extends Comment{

	private static final long serialVersionUID = 2300617318145233064L;

	@ManyToOne
    @JoinColumn(name = "id_notice", nullable = true)
    @JsonIgnore
    private Notice notice;
    
	public NoticeComment() {
		super();
	}

	public NoticeComment(Notice notice) {
		super();
		this.notice = notice;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}
