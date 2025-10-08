package com.f1.Formula1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Comment;
import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.entities.NoticeComment;
import com.f1.Formula1.entities.TopicComment;
import com.f1.Formula1.repositories.ICommentRepository;
import com.f1.Formula1.repositories.INoticeRepository;
import com.f1.Formula1.repositories.ITopicRepository;

@Service
public class CommentService extends AbstractCRUDService<Comment, ICommentRepository> {

	@Autowired
	private INoticeRepository noticeRepository;

	@Autowired
	private ITopicRepository topicRepository;

	public CommentService(ICommentRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Comment comment) {
		return comment.getId();
	}

	/*
	 * Notices comments
	 */
	public List<NoticeComment> getCommentsByNoticeId(Long noticeId) {
		return repository.findByNoticeId(noticeId);

	}

	public NoticeComment saveCommentToNotice(Long noticeId, NoticeComment comment) {
		Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("Notice not found"));

		comment.setNotice(notice);
		return repository.save(comment);
	}

	public List<NoticeComment> getNoticeCommentsSortedByDate(Long noticeId, Sort.Direction direction) {
		return repository.findByNoticeId(noticeId, Sort.by(direction, "date"));
	}

	/*
	 * Topics comments
	 */
	public List<TopicComment> getCommentsByTopicId(Long topicId) {
		return repository.findByTopicId(topicId);

	}

	public TopicComment saveCommentToTopic(Long topicId, TopicComment comment) {
		Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));

		comment.setTopic(null);
		return repository.save(comment);
	}

	public List<TopicComment> getTopicCommentsSortedByDate(Long topicId, Sort.Direction direction) {
		return repository.findByTopicId(topicId, Sort.by(direction, "date"));
	}

}
