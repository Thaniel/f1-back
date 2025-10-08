package com.f1.Formula1.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.entities.NoticeComment;
import com.f1.Formula1.services.CommentService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/notices/{noticeId}/comments")
public class NoticeCommentController {

	@Autowired
	private CommentService commentService;

	/*
	 * Get Comments on a Notice
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.comments.all")
	public ResponseEntity<List<NoticeComment>> getCommentsByNoticeId(@PathVariable Long noticeId) {
		List<NoticeComment> comments = commentService.getCommentsByNoticeId(noticeId);

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found on notice with id: " + noticeId)
					.build();
		}

		return ResponseEntity.ok(comments);
	}
	
	/*
	 * Create Comment on a notice
	 */
	@PostMapping
	@Timed("notices.comments.create")
	public ResponseEntity<NoticeComment> saveCommentToNotice(@PathVariable Long noticeId,
			@RequestBody NoticeComment comment) {
		try {
			NoticeComment commentSaved = commentService.saveCommentToNotice(noticeId, comment);
			
			String path = "/notices/" + noticeId + "/comments";
			URI uri = new URI(path.concat(commentSaved.getId().toString()));

			return ResponseEntity.created(uri).body(commentSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/*
	 * Get Comments on a Notice Sort by Date
	 */
	@GetMapping(value = "/sorted", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.comments.sorted")
	public ResponseEntity<List<NoticeComment>> getCommentsSortedByDate(@PathVariable Long noticeId, @RequestParam(defaultValue = "asc") String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		List<NoticeComment> comments = commentService.getNoticeCommentsSortedByDate(noticeId, direction);

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found on notice with id: " + noticeId)
					.build();
		}

		return ResponseEntity.ok(comments);
	}

}
