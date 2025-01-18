package com.f1.Formula1.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.dto.CommentDTO;
import com.f1.Formula1.model.Comment;
import com.f1.Formula1.service.CommentService;

@RestController
@RequestMapping("/comments/")
public class CommentRestController {
	// TODO RequestMapping

	@Autowired
	private CommentService commentService;

	private String path = "/comments/";

	/*
	 * Get Comments
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = commentService.getAll();

		if (comments.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No comments found").build();
		}

		return ResponseEntity.ok(comments);
	}

	/*
	 * Get Comment by Id
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		Comment comment = commentService.getById(id);

		if (comment == null) {
			return ResponseEntity.noContent().header("message", "Comment not found with id: " + id).build();
		}

		return ResponseEntity.ok(comment);
	}

	/*
	 * Create Comment
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Comment> saveComment(@RequestBody CommentDTO commentDTO) {
		try {
			Comment commentSaved = null;
			URI uri = null;
			
			if (commentDTO.getNoticeId() != null) {
				commentSaved = commentService.createInANotice(commentDTO);
				uri = new URI(path.concat(commentSaved.getId().toString()));
			} else {
				commentSaved = commentService.createInANotice(commentDTO);
				uri = new URI(path.concat(commentSaved.getId().toString()));
			}

			return ResponseEntity.created(uri).body(commentSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update Comment
	 */
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> updateCommentById(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
		try {
			Comment updatedComment = null;
			URI uri = null;
			
			if (commentDTO.getNoticeId() != null) {
				updatedComment = commentService.updateInANotice(commentDTO);
				uri = new URI(path.concat(updatedComment.getId().toString()));
			} else {
				updatedComment = commentService.updateInANotice(commentDTO);
				uri = new URI(path.concat(updatedComment.getId().toString()));
			}

			return ResponseEntity.created(uri).body(updatedComment);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Delete Comment
	 */
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
		Comment commentDeleted = commentService.delete(id);

		if (commentDeleted == null) {
			return ResponseEntity.notFound().header("message", "Comment not found with id: " + id).build();
		}

		return ResponseEntity.ok(commentDeleted);
	}
}
