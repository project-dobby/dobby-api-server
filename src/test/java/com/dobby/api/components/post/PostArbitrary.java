package com.dobby.api.components.post;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;

import lombok.Setter;

import com.dobby.api.test.ArbitraryUtils;

@Setter
public class PostArbitrary {
	private Arbitrary<UUID> id = Arbitraries.randomValue(r -> UUID.randomUUID());
	private Arbitrary<String> title = Arbitraries.strings()
			.alpha()
			.ofMinLength(1)
			.ofMaxLength(100);
	private Arbitrary<String> body = Arbitraries.strings()
			.alpha()
			.ofMinLength(1)
			.ofMaxLength(100);
	private Arbitrary<Instant> createdAt = Arbitraries.randomValue(r -> Instant.now());

	public static Post defaultOne() {
		return new PostArbitrary().newOne();
	}

	public Arbitrary<Post> arbitrary() {
		return Combinators.combine(
				this.id, this.title, this.body, this.createdAt)
				.as((id, title, body, createdAt) ->
						Post.builder()
								.title(title)
								.body(body)
								.build());
	}

	public Post newOne() {
		return ArbitraryUtils.one(this.arbitrary());
	}

	public List<Post> newList(int size) {
		return ArbitraryUtils.list(this.arbitrary(), size);
	}
}
