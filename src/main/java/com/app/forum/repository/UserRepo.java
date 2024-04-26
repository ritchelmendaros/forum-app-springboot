package com.app.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.forum.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
