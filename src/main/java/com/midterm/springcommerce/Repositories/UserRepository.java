package com.midterm.springcommerce.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midterm.springcommerce.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
