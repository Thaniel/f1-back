package com.f1.Formula1.controllers;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.services.TopicService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/topics")
public class TopicRestController {

	@Autowired
	private TopicService topicService;

	private String path = "/topics/";

	/*
	 * Get Topics
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.all")
	public ResponseEntity<List<Topic>> getAllTopics() {
		List<Topic> topics = topicService.getAll();
		
		if (topics.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No topics found").build();
		}
		
		return ResponseEntity.ok(topics);
	}

	/*
	 * Get Topic by Id
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.by.id")
	public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
		Topic topic = topicService.getById(id);
		
		if(topic == null) {
			return ResponseEntity.noContent().header("message", "Topic not found with id: " + id).build();
		}
				
		return ResponseEntity.ok(topic);
	}

	/*
	 * Create Topic
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.create")
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {
		try {
			Topic topicSaved = topicService.create(topic);
			URI uri = new URI(path.concat(topicSaved.getId().toString()));

			return ResponseEntity.created(uri).body(topicSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update Topic
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> updateTopicById(@PathVariable long id, @RequestBody Topic topic) {
		Topic updatedTopic = topicService.update(topic);

		if (updatedTopic == null) {
			return ResponseEntity.notFound().header("message", "Topic not found with id: " + id).build();
		}
		return ResponseEntity.ok(updatedTopic);
	}

	
	/*
	 * Delete Topic
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
		Topic topicDeleted = topicService.delete(id);	

		if (topicDeleted == null) {
			return ResponseEntity.notFound().header("message", "Topic not found with id: " + id).build();
		}

		return ResponseEntity.ok(topicDeleted);
	}


	/*
	 * Get Topic sorted by Date
	 */	
	@GetMapping(value = "/sorted", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("topics.sorted.by.date")
	public ResponseEntity<List<Topic>> getTopicsSortedByDate(@RequestParam(defaultValue = "asc") String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		List<Topic> topics = topicService.getTopicsSortedByDate(direction);

		if (topics.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No topics found").build();
		}

		return ResponseEntity.ok(topics);
	}
	
	/*
	 * Get Topic sorted by Number of comments
	 */
	@GetMapping(value = "/sortedByComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Topic>> getTopicsByNumberOfComments() {
		List<Topic> topics = topicService.getTopicsByNumberOfComments();

		if (topics.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No topics found").build();
		}

		return ResponseEntity.ok(topics);
	}
}
