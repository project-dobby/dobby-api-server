package com.dobby.api.components.post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import com.dobby.api.components.comment.Comment;
import com.dobby.api.components.project.Project;
import com.dobby.api.supports.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
	@Setter(AccessLevel.NONE)
	private UUID id = UUID.randomUUID();
	@Size(min = 1, max = 100)
	@NotNull
	private String title;

	@Lob
	private String body;

	@ManyToOne
	@JoinColumn(name = "projectId")
	@NotNull
	private Project project;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "postId")
	@BatchSize(size = 100)
	private List<Comment> comments = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	private Instant createdAt = Instant.now();

	@JsonCreator
	@Builder
	Post(
		@JsonProperty("title") String title,
		@JsonProperty("body") String body,
		@JsonProperty("project") Project project) {
		this.title = title;
		this.body = body;
		this.project = project;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
}