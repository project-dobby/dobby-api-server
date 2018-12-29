package com.dobby.api.components.project;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.dobby.api.components.post.Post;
import com.dobby.api.components.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cnabro
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(of = "id")
public class Project {
	@Id
	@Setter(AccessLevel.NONE)
	private String id;

	private String profileImage;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private List<User> members;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private List<User> owners;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private List<Post> posts;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	private Instant createdAt = Instant.now();

	@JsonCreator
	@Builder
	Project(
		@JsonProperty("id") String id) {
		this.id = id;
	}
}
