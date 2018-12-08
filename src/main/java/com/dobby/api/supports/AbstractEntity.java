package com.dobby.api.supports;

import java.util.UUID;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractEntity implements Persistable<UUID> {
	@Transient
	private transient boolean persistent = false;

	@JsonIgnore
	@Override
	public boolean isNew() {
		return !this.persistent;
	}

	@PostPersist
	@PostLoad
	void setPersistent() {
		this.persistent = true;
	}
}
