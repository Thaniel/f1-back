package com.f1.Formula1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.entities.Topic;

public interface ITopicRepository extends JpaRepository<Topic, Long>{

}
