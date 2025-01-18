package com.f1.Formula1.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.model.Topic;
import com.f1.Formula1.service.TopicService;

@RestController
@RequestMapping("/topics/")
public class TopicRestController {
	@Autowired
	private TopicService topicService;

	private String path = "/topics/";

	@GetMapping
	private ResponseEntity<List<Topic>> getAllTopics() {
		return ResponseEntity.ok(topicService.findAll());
	}

	@GetMapping("{id}")
	private ResponseEntity<Optional<Topic>> getTopicById(@PathVariable Long id) {
		return ResponseEntity.ok(topicService.findById(id));
	}

	@PostMapping
	private ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {
		try {
			Topic topicSaved = topicService.save(topic);
			URI uri = new URI(path.concat(topicSaved.getId().toString()));

			return ResponseEntity.created(uri).body(topicSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping(value = "delete/{id}")
	private ResponseEntity<Boolean> deleteTopic(@PathVariable Long id) {
		topicService.deleteById(id);
		return ResponseEntity.ok(!(topicService.findById(id) != null));
	}
}
