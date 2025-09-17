package com.f1.Formula1.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.f1.Formula1.model.Notice;

public interface INoticeRepository extends JpaRepository<Notice, Long>{
	
	List<Notice> findAll(Sort sort);
	
	List<Notice> findByUserId(Long userId);

	@Query("SELECT n FROM Notice n WHERE YEAR(n.date) = :year")
	List<Notice> findByYear(@Param("year") int year);
	
	@Query("SELECT n FROM Notice n WHERE YEAR(n.date) = :year AND MONTH(n.date) = :month")
	List<Notice> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
