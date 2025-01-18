package com.f1.Formula1.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.model.Notice;

public interface INoticeRepository extends JpaRepository<Notice, Long>{
	
	List<Notice> findAll(Sort sort);
	
	List<Notice> findByUserId(Long userId);
}
