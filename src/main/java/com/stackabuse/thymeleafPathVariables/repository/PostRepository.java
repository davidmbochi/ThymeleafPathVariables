package com.stackabuse.thymeleafPathVariables.repository;

import com.stackabuse.thymeleafPathVariables.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
