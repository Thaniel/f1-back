package com.f1.Formula1.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.model.Notice;
import com.f1.Formula1.repository.INoticeRepository;

@Service
public class NoticeService extends AbstractCRUDService<Notice, INoticeRepository> {

	public NoticeService(INoticeRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Notice notice) {
		return notice.getId();
	}
	
	public List<Notice> getNoticesOrderedByDate (){
		return repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
	}
	
	public List<Notice> getNoticesByUserId (Long userId){
		return repository.findByUserId(userId);
	}
	
	public List<Notice> getNoticesByNumberOfComments (){
		List<Notice> notices = repository.findAll();
		
		notices.sort((n1, n2) -> Integer.compare(n2.getComments().size(), n1.getComments().size()));
		
		/*
		 * Other option 
		 * 		Making the query on INoticeRepository -> Better for big collections 
		 * 
		 *  @Query("SELECT n FROM Notice n LEFT JOIN n.comments c GROUP BY n ORDER BY COUNT(c) DESC")
		 *  List<Notice> findNoticesOrderedByNumberOfComments();
		 */
		
		return notices;
	}
}
