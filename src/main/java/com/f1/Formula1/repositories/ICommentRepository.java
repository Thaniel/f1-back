package com.f1.Formula1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.entities.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long>{

}
