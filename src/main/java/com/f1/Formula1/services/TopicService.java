package com.f1.Formula1.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.repositories.ITopicRepository;

@Service
public class TopicService extends AbstractCRUDService<Topic, ITopicRepository>{
	
	public TopicService(ITopicRepository repository) {
		super(repository);
	}
	
	@Override
	protected Long getEntityId(Topic topic) {
		return topic.getId();
	}
	
	public List<Topic> getTopicsSortedByDate(Sort.Direction direction){
		return repository.findAll(Sort.by(direction, "date"));
	}
	
	public List<Topic> getTopicsByNumberOfComments(){
		List<Topic> topics = repository.findAll();
		
		topics.sort((t1, t2) -> Integer.compare(t2.getComments().size(), t1.getComments().size()));
		
		return topics;
	}
}
