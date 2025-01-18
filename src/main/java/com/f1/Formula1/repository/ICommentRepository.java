package com.f1.Formula1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.model.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long>{

}
