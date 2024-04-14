package com.gustavosthel.registerauthapi.repositories;

import com.gustavosthel.registerauthapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
