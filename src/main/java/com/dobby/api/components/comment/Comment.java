package com.dobby.api.components.comment;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

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
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(of = "id")
public class Comment extends AbstractEntity {
	@Id
	@Column(columnDefinition = "binary(16)")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	private UUID id = UUID.randomUUID();

	@Lob
	private String body;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	private Instant createdAt = Instant.now();

	@JsonCreator
	@Builder
	Comment(String body) {
		this.body = body;
	}
}
