package com.dobby.api.components.user;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.dobby.api.components.project.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
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
public class User {
	@Id
	private String id;

	@NotNull
	private String nickName;

	@NotNull
	private String email;

	private UserType type = UserType.USER;

	private String profileImage;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<Project> projects;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	private Instant createdAt = Instant.now();

}
