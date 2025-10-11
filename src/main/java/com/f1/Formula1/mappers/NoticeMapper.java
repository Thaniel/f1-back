package com.f1.Formula1.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.dtos.NoticeDTO;
import com.f1.Formula1.entities.Notice;

public class NoticeMapper {
	
	public static NoticeDTO toDTO(Notice notice) {
		NoticeDTO dto = null;
		
		if (notice != null) {
			dto = new NoticeDTO(notice);
		}

		return dto;
	}
	
    public static List<NoticeDTO> toDTOList(List<Notice> notices) {
        return notices.stream().map(NoticeMapper::toDTO).collect(Collectors.toList());
    }
}
