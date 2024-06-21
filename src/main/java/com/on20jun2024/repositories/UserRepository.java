package com.on20jun2024.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.on20jun2024.entity.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
   public UserInfo findByUsername(String username);
}