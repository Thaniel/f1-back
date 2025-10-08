package com.f1.Formula1.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.entities.Comment;
import com.f1.Formula1.entities.NoticeComment;
import com.f1.Formula1.entities.TopicComment;

public interface ICommentRepository extends JpaRepository<Comment, Long>{
	
	List<NoticeComment> findByNoticeId(Long noticeId);
	
	List<NoticeComment> findByNoticeId(Long noticeId, Sort sort);

	
	List<TopicComment> findByTopicId(Long topicId);
	
	List<TopicComment> findByTopicId(Long topicId, Sort sort);

}
