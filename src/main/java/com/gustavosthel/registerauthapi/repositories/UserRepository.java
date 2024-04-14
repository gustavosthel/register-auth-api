package com.gustavosthel.registerauthapi.repositories;

import com.gustavosthel.registerauthapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
