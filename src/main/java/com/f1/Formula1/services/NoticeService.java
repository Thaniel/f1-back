package com.f1.Formula1.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.repositories.INoticeRepository;

@Service
public class NoticeService extends AbstractCRUDService<Notice, INoticeRepository> {

	public NoticeService(INoticeRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Notice notice) {
		return notice.getId();
	}

	public List<Notice> getNoticesSortedByDate(Sort.Direction direction) {
		return repository.findAll(Sort.by(direction, "date"));
	}

	public List<Notice> getNoticesByUserId(Long userId) {
		return repository.findByUserId(userId);
	}

	public List<Notice> getNoticesByNumberOfComments(Sort.Direction direction){
		if (direction == Sort.Direction.ASC) {
            return repository.findAllOrderByCommentCountAsc();
        } else {
            return repository.findAllOrderByCommentCountDesc();
        }
	}

	public List<Notice> getNoticesByYear(Integer year) {
		return repository.findByYear(year);
	}

	public List<Notice> getNoticesByYearAndMonth(Integer year, Integer month) {
		return repository.findByYearAndMonth(year, month);
	}
}
