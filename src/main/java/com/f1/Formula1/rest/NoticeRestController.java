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

import com.f1.Formula1.model.Notice;
import com.f1.Formula1.service.NoticeService;

@RestController
@RequestMapping("/notices/")
public class NoticeRestController {

	@Autowired
	private NoticeService noticeService;

	private String path = "/notices/";

	/*
	 * Get Notices
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Notice>> getAllNotices() {
		List<Notice> notices = noticeService.getAll();

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notice by Id
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
		Notice notice = noticeService.getById(id);

		if (notice == null) {
			return ResponseEntity.noContent().header("message", "Notice not found with id: " + id).build();
		}

		return ResponseEntity.ok(notice);
	}

	/*
	 * Create Notice
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Notice> saveNotice(@RequestBody Notice notice) {
		try {
			Notice noticeSaved = noticeService.create(notice);
			URI uri = new URI(path.concat(noticeSaved.getId().toString()));

			return ResponseEntity.created(uri).body(noticeSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update Notice
	 */
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> updateNoticeById(@PathVariable long id, @RequestBody Notice notice) {
		Notice updatedNotice = noticeService.update(notice);

		if (updatedNotice == null) {
			return ResponseEntity.notFound().header("message", "Notice not found with id: " + id).build();
		}
		return ResponseEntity.ok(updatedNotice);
	}

	/*
	 * Delete Notice
	 */
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Notice> deleteNotice(@PathVariable Long id) {
		Notice noticeDeleted = noticeService.delete(id);

		if (noticeDeleted == null) {
			return ResponseEntity.notFound().header("message", "Notice not found with id: " + id).build();
		}

		return ResponseEntity.ok(noticeDeleted);
	}

	/*
	 * Get Notices Ordered by Date
	 */
	@GetMapping(value = "/ordered", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Notice>> getNoticesOrderedByDate() {
		List<Notice> notices = noticeService.getNoticesOrderedByDate();

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices by User Id
	 */
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Notice>> getNoticesByUserId(@PathVariable Long userId) {
		List<Notice> notices = noticeService.getNoticesByUserId(userId);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found for user id: " + userId).build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices Sorted by Number of Comments
	 */
	@GetMapping(value = "/sortedNumberComments", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Notice>> getNoticesByNumberOfComments() {
		List<Notice> notices = noticeService.getNoticesByNumberOfComments();

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}
}
