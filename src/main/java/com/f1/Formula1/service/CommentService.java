package com.f1.Formula1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1.Formula1.dto.CommentDTO;
import com.f1.Formula1.model.Comment;
import com.f1.Formula1.model.Notice;
import com.f1.Formula1.repository.ICommentRepository;
import com.f1.Formula1.repository.INoticeRepository;

@Service
public class CommentService extends AbstractCRUDService<Comment, ICommentRepository> {

	@Autowired
	private INoticeRepository noticeRepository;

	@Autowired
	private ICommentRepository commentRepository;

	public CommentService(ICommentRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Comment comment) {
		return comment.getId();
	}

	public Comment createInANotice(CommentDTO commentDTO) {
		Optional<Notice> notice = noticeRepository.findById(commentDTO.getNoticeId());

		Comment comment = new Comment();

		if (notice.isPresent()) {
			comment.setDate(commentDTO.getDate());
			comment.setText(commentDTO.getText());
			comment.setUser(commentDTO.getUser());
			comment.setNotice(notice.get());

			comment = commentRepository.save(comment);
		}

		return comment;
	}

	public Comment updateInANotice(CommentDTO commentDTO) {
		Optional<Comment> comment = commentRepository.findById(commentDTO.getId());
		
		Comment commentReturn = new Comment();
		
		if (comment.isPresent()) {
			comment.get().setText(commentDTO.getText());

			commentReturn = commentRepository.save(comment.get());
		}

		return commentReturn;
	
	}

	/*
	 * public List<Comment> findCommentsByTopic(Long idTopic) { List<Comment>
	 * commentsResponse = new ArrayList<>(); List<Comment> allComments =
	 * commentRepository.findAll();
	 * 
	 * allComments.stream() .filter(c -> c.getTopic().getId() == idTopic)
	 * .forEach(commentsResponse::add);
	 * 
	 * return commentsResponse; }
	 */
}
