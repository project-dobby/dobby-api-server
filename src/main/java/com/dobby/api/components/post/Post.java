package com.dobby.api.components.post;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dobby.api.supports.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(of = "id")
public class Post extends AbstractEntity {
	@Id
	@Column(columnDefinition = "binary(16)")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@Size(min = 1, max = 100)
	@NotNull
	private String title;

	@Lob
	private String body;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	private Instant createdAt = Instant.now();

	@JsonCreator
	@Builder
	Post(
			@JsonProperty("title") String title,
			@JsonProperty("body") String body) {
		this.title = title;
		this.body = body;
	}
}
