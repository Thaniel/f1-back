package com.f1.Formula1.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.entities.Comment;
import com.f1.Formula1.services.CommentService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	/*
	 * Get Comments (Notice or Topic)
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = commentService.getAll();

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found").build();
		}

		return ResponseEntity.ok(comments);
	}
	
	/*
	 * Get Comment by Id (Notice or Topic)
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("comments.getById")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		Comment comment = commentService.getById(id);

		if (comment == null) {
			return ResponseEntity.noContent().header("message", "Comment not found with id: " + id).build();
		}

		return ResponseEntity.ok(comment);
	}
	
	/*
	 * Update Comment (Notice or Topic)
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("comments.update")
	public ResponseEntity<Comment> updateCommentById(@PathVariable long id, @RequestBody Comment comment) {
		Comment updatedComment = commentService.update(comment);
		
		if(updatedComment == null) {
			return ResponseEntity.notFound().header("message", "Comment not found with id: " + id).build();
		}
		
		return ResponseEntity.ok(updatedComment);
	}

	/*
	 * Delete Comment (Notice or Topic)
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("comments.delete")
	public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
		Comment commentDeleted = commentService.delete(id);

		if (commentDeleted == null) {
			return ResponseEntity.notFound().header("message", "Comment not found with id: " + id).build();
		}

		return ResponseEntity.ok(commentDeleted);
	}
	
}
