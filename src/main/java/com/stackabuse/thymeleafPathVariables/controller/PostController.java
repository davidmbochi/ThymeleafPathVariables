package com.stackabuse.thymeleafPathVariables.controller;

import com.stackabuse.thymeleafPathVariables.model.Post;
import com.stackabuse.thymeleafPathVariables.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/find/all")
    public String viewAllPostsAndComments(Model model){
        List<Post> postList = postRepository.findAll();
        model.addAttribute("thePosts",postList);
        return "all-posts";
    }

    @GetMapping("/find/one/{postId}")
    public String viewPost(@PathVariable("postId") Long postId,
                                 Model model){
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        model.addAttribute("post",post);
        return "view-post";
    }

    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId,
                             Model model){
        Post post = postRepository.getOne(postId);
        System.out.println(post.toString());
        model.addAttribute("post",post);

        return "update-post";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute("post") Post post){
        postRepository.save(post);
        return "redirect:/post/find/all";
    }

    @GetMapping("/delete/one/{postId}")
    public String deletePost(@PathVariable("postId") Long postId){
        Post post = postRepository.getOne(postId);
        postRepository.delete(post);
        return "redirect:/post/find/all";
    }
}
