package com.f1.Formula1.mappers;


import com.f1.Formula1.dtos.CommentDTO;
import com.f1.Formula1.dtos.DriverWithoutTeamDTO;
import com.f1.Formula1.entities.Comment;
import com.f1.Formula1.entities.Driver;

public class CommentMapper {
	
	/*public static CommentDTO toDTO(Comment comment) {
		CommentDTO dto = new CommentDTO();
		
		dto.setId(comment.getId());
		dto.setText(comment.getText());
		dto.setUser(comment.getUser());
		dto.setNoticeId(comment.getNotice().getId());
		dto.setDate(comment.getDate());

		
		dto.setTeam(CommentMapper.toDTOWithoutTopic(comment);
		
		return dto;
	}

	public static CommentWithoutTopicDTO toDTOWithoutTopic(Comment comment) {
		CommentWithoutTopicDTO dto = new CommentWithoutTopicDTO();
		
		dto.setId(driver.getId());
		dto.setFirstName(driver.getFirstName());
		dto.setLastName(driver.getLastName());
		dto.setCountry(driver.getCountry());
		dto.setBirthDate(driver.getBirthDate());
		dto.setPoints(driver.getPoints());
		dto.setTitles(driver.getTitles());
		dto.setImage(driver.getImage());
		
		return dto;
	}*/
}