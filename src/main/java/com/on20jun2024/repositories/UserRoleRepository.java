package com.on20jun2024.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.on20jun2024.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	
	 UserRole findByName(String name);
	
}
