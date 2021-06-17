package com.stackabuse.thymeleafPathVariables.repository;

import com.stackabuse.thymeleafPathVariables.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
