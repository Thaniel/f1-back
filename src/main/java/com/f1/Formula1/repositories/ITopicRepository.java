package com.f1.Formula1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.f1.Formula1.entities.Topic;

public interface ITopicRepository extends JpaRepository<Topic, Long>{

    @Query("SELECT t FROM Topic t LEFT JOIN t.comments c GROUP BY t ORDER BY COUNT(c) DESC")
    List<Topic> findAllOrderByCommentCountDesc();

    @Query("SELECT t FROM Topic t LEFT JOIN t.comments c GROUP BY t ORDER BY COUNT(c) ASC")
    List<Topic> findAllOrderByCommentCountAsc();
}
