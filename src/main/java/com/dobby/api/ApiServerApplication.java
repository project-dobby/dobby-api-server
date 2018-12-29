package com.dobby.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.dobby.api.components.comment.Comment;
import com.dobby.api.components.post.Post;
import com.dobby.api.components.post.PostRepository;
import com.dobby.api.components.project.Project;
import com.dobby.api.components.project.ProjectRepository;

@SpringBootApplication
public class ApiServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
	}

	@Bean
	@Profile("!test")
	ApplicationRunner initialize(PostRepository postRepository, ProjectRepository projectRepository) {
		return args -> {
			List<Project> projects = new ArrayList<>();
			Project project = Project.builder().id("dobby_team").build();
			projects.add(project);

			projectRepository.saveAll(projects);

			List<Post> posts = new ArrayList<>();
			posts.add(Post.builder().title("title1").body("body1").project(project).build());
			posts.add(Post.builder().title("title2").body("body2").project(project).build());
			posts.add(Post.builder().title("title3").body("body3").project(project).build());

			posts.forEach(p -> p.addComment(Comment.builder()
				.body("comment")
				.build()));
			postRepository.saveAll(posts);
		};
	}

}
