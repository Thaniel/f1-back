package com.f1.Formula1.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.dtos.TopicDTO;
import com.f1.Formula1.entities.Topic;

public class TopicMapper {
	
	public static TopicDTO toDTO(Topic topic) {
		TopicDTO dto = null;
		
		if (topic != null) {
			dto = new TopicDTO(topic);
		}

		return dto;
	}
	
    public static List<TopicDTO> toDTOList(List<Topic> topics) {
        return topics.stream().map(TopicMapper::toDTO).collect(Collectors.toList());
    }

}
