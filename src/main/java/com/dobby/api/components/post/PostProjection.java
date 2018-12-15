package com.dobby.api.components.post;

import java.time.Instant;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.dobby.api.components.comment.Comment;

@Projection(name = "comments", types = {Post.class})
public interface PostProjection {
	String getTitle();

	String getBody();

	List<Comment> getComments();

	Instant getCreatedAt();
}
