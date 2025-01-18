package com.f1.Formula1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.model.Topic;

public interface ITopicRepository extends JpaRepository<Topic, Long>{

}
