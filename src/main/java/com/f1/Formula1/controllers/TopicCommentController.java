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


import com.f1.Formula1.entities.TopicComment;
import com.f1.Formula1.services.CommentService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/topics/{topicId}/comments")
public class TopicCommentController {

	@Autowired
	private CommentService commentService;

	/*
	 * Get Comments on a Topic
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.comments.all")
	public ResponseEntity<List<TopicComment>> getCommentsByTopicId(@PathVariable Long topicId) {
		List<TopicComment> comments = commentService.getCommentsByTopicId(topicId);

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found on topic with id: " + topicId)
					.build();
		}

		return ResponseEntity.ok(comments);
	}
	
	/*
	 * Create Comment on a topic
	 */
	@PostMapping
	@Timed("topics.comments.create")
	public ResponseEntity<TopicComment> saveCommentToTopic(@PathVariable Long topicId,
			@RequestBody TopicComment comment) {
		try {
			TopicComment commentSaved = commentService.saveCommentToTopic(topicId, comment);
			
			String path = "/topics/" + topicId + "/comments";
			URI uri = new URI(path.concat(commentSaved.getId().toString()));

			return ResponseEntity.created(uri).body(commentSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/*
	 * Get Comments on a Topic Sort by Date
	 */
	@GetMapping(value = "/sorted", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.comments.sorted")
	public ResponseEntity<List<TopicComment>> getCommentsSortedByDate(@PathVariable Long topicId,@RequestParam(defaultValue = "desc") String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		List<TopicComment> comments = commentService.getTopicCommentsSortedByDate(topicId, direction);

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found on topic with id: " + topicId)
					.build();
		}

		return ResponseEntity.ok(comments);
	}

}
