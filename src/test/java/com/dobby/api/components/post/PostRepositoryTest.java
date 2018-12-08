package com.dobby.api.components.post;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DataJpaTest
class PostRepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private PostRepository sut;

	@Test
	void save() {
		// Given
		Post post = new Post();
		post.setTitle("title");
		post.setBody("body");

		// When
		this.sut.save(post);
		this.flushAndClear();

		// Then
		Post actual = this.em.find(Post.class, post.getId());
		assertThat(actual.getTitle()).isEqualTo(post.getTitle());
		assertThat(actual.getBody()).isEqualTo(post.getBody());
	}

	@Test
	void findByTitleStartsWith() {
		// Given
		Post post = new Post();
		post.setTitle("title");
		post.setBody("body");

		Post post2 = new Post();
		post2.setTitle("not search");
		post2.setBody("body 2");

		this.em.persist(post);
		this.em.persist(post2);
		this.flushAndClear();

		// When
		Page<Post> actual = this.sut.findByTitleStartsWith("ti", Pageable.unpaged());

		// Then
		assertThat(actual).hasSize(1);
		assertThat(actual.getContent().get(0).getTitle()).isEqualTo(post.getTitle());
	}

	@Test
	void findById() {
		// Given
		Post post = PostArbitrary.defaultOne();

		this.em.persist(post);
		this.flushAndClear();

		// When
		Optional<Post> actual = this.sut.findById(post.getId());

		// Then
		assertThat(actual).isPresent();
		assertThat(actual.get().getTitle()).isEqualTo(post.getTitle());
		assertThat(actual.get().getBody()).isEqualTo(post.getBody());
	}

	private void flushAndClear() {
		this.em.flush();
		this.em.clear();
	}
}
