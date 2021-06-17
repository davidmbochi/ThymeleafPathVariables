package com.stackabuse.thymeleafPathVariables.controller;

import com.stackabuse.thymeleafPathVariables.model.Comment;
import com.stackabuse.thymeleafPathVariables.model.Post;
import com.stackabuse.thymeleafPathVariables.repository.CommentRepository;
import com.stackabuse.thymeleafPathVariables.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository,
                             PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/find/one/{commentId}/post/{postId}")
    public String viewComment(@PathVariable("commentId") Long commentId,
                              @PathVariable("postId") Long postId,
                              Model model){
        Post post = postRepository.getOne(postId);

        List<Comment> comments = post.getComments();

        for (Comment comment : comments) {
            if (comment.getId() == commentId){
                model.addAttribute("comment",comment);
            }
        }

        return "view-comment";

    }
}
