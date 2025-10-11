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
	
	public List<Topic> getTopicsByNumberOfComments(Sort.Direction direction){
		if (direction == Sort.Direction.ASC) {
            return repository.findAllOrderByCommentCountAsc();
        } else {
            return repository.findAllOrderByCommentCountDesc();
        }
	}
}
