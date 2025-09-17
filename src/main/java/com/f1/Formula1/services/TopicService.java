package com.f1.Formula1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.repositories.ITopicRepository;

@Service
public class TopicService {
	@Autowired
	private ITopicRepository topicRepository;

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public <S extends Topic> S save(S entity) {
		return topicRepository.save(entity);
	}

	public Optional<Topic> findById(Long id) {
		return topicRepository.findById(id);
	}

	public void deleteById(Long id) {
		topicRepository.deleteById(id);
	}

	public List<Topic> findTopicsByUser(Long idUser) {
		List<Topic> topicsResponse = new ArrayList<>();
		List<Topic> allTopics = topicRepository.findAll();

		allTopics.stream()
			.filter(t -> t.getUser().getId() == idUser)
			.forEach(topicsResponse::add);

		return topicsResponse;
	}
}
