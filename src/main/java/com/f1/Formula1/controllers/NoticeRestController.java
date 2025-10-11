package com.f1.Formula1.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.services.NoticeService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/notices")
public class NoticeRestController {

	@Autowired
	private NoticeService noticeService;

	private final String path = "/notices/";

	/*
	 * Get Notices
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.all")
	public ResponseEntity<List<Notice>> getAllNotices() {
		List<Notice> notices = noticeService.getAll();

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notice by Id
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.by.id")
	public ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
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
	@Timed("notices.create")
	public ResponseEntity<Notice> saveNotice(@RequestBody Notice notice) {
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
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> deleteNotice(@PathVariable Long id) {
		Notice noticeDeleted = noticeService.delete(id);

		if (noticeDeleted == null) {
			return ResponseEntity.notFound().header("message", "Notice not found with id: " + id).build();
		}

		return ResponseEntity.ok(noticeDeleted);
	}

	/*
	 * Get Notices Sort by Date
	 */
	@GetMapping(value = "/sorted", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.sorted.by.date")
	public ResponseEntity<List<Notice>> getNoticesSortDescByDate(@RequestParam(defaultValue = "desc") String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		List<Notice> notices = noticeService.getNoticesSortedByDate(direction);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices by User Id
	 */
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.by.user")
	public ResponseEntity<List<Notice>> getNoticesByUserId(@PathVariable Long userId) {
		List<Notice> notices = noticeService.getNoticesByUserId(userId);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found for user id: " + userId).build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices Sorted by Number of Comments
	 */
	@GetMapping(value = "/sortedByComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Notice>> getNoticesByNumberOfComments(@RequestParam(defaultValue = "desc") String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		List<Notice> notices = noticeService.getNoticesByNumberOfComments(direction);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found").build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices by Year
	 */
	@GetMapping(value = "/year/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.by.year")
	public ResponseEntity<List<Notice>> getNoticesByYear(@PathVariable("year") Integer year) {
		if (year == null || year < 1900 || year > Year.now().getValue()) {
			return ResponseEntity.badRequest().header("message", "Invalid year: " + year).build();
		}

		List<Notice> notices = noticeService.getNoticesByYear(year);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No notices found for year: " + year).build();
		}

		return ResponseEntity.ok(notices);
	}

	/*
	 * Get Notices by Year and Month
	 */
	@GetMapping(value = "/year/{year}/month/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("notices.by.year.month")
	public ResponseEntity<List<Notice>> getNoticesByYear(@PathVariable("year") Integer year,
			@PathVariable("month") Integer month) {

		if (year == null || year < 1900 || year > Year.now().getValue()) {
			return ResponseEntity.badRequest().header("message", "Invalid year: " + year).build();
		} else if (month == null || month < 1 || month > 12) {
			return ResponseEntity.badRequest().header("message", "Invalid month: " + month).build();
		}

		List<Notice> notices = noticeService.getNoticesByYearAndMonth(year, month);

		if (notices.isEmpty()) {
			return ResponseEntity.noContent()
					.header("message", "No notices found for year: " + year + " and month: " + month).build();
		}

		return ResponseEntity.ok(notices);
	}
}
