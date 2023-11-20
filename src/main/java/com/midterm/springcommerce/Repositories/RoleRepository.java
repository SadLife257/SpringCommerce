package com.midterm.springcommerce.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.Role;
import com.midterm.springcommerce.Utilities.ERole;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
