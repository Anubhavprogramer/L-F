package com.lostandfound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lostandfound.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
