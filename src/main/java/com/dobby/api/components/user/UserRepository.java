package com.dobby.api.components.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cnabro
 */
public interface UserRepository extends JpaRepository<User, String> {
}
