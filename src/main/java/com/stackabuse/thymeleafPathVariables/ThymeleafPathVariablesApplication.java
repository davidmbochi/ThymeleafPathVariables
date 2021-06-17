package com.stackabuse.thymeleafPathVariables;

import com.stackabuse.thymeleafPathVariables.model.Comment;
import com.stackabuse.thymeleafPathVariables.model.Post;
import com.stackabuse.thymeleafPathVariables.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ThymeleafPathVariablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafPathVariablesApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository){
		return args -> {
			List<Post> postList = List.of(
					new Post("taking the dog to the vet"),
					new Post("today is a rainy day"),
					new Post("implemented a cool feature using java"),
					new Post("just turned 30 years old !!!"),
					new Post("KFC has the best chicken")
			);

			postRepository.saveAll(postList);

			Optional<Post> postOne = postRepository.findById(1L);

			if (postOne.isPresent()){
				Post post1 = postOne.get();


				/*
				 * The comments we have added will be saved to database
				 * due to CascadeType.ALL
				 * */
				post1.addComment(new Comment("nice dog"));
				post1.addComment(new Comment("how cool"));
				post1.addComment(new Comment("recover well"));

				postRepository.save(post1);
			}

			Optional<Post> postTwo = postRepository.findById(2L);

			if (postTwo.isPresent()){
				Post post2 = postTwo.get();
				post2.addComment(new Comment("Carry an umbrella with you"));
				post2.addComment(new Comment("Stay at home to avoid the rain"));
				post2.addComment(new Comment("Ware heavy clothing to  avoid getting wet"));

				postRepository.save(post2);

			}


		};

	};


}
