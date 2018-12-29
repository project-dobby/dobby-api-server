package com.dobby.api.components.project;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cnabro
 */
public interface ProjectRepository extends JpaRepository<Project, String> {
}
