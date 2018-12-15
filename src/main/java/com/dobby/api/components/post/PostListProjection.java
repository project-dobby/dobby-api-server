package com.dobby.api.components.post;

import java.time.Instant;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "list", types = {Post.class})
public interface PostListProjection {
	String getTitle();

	Instant getCreatedAt();
}
