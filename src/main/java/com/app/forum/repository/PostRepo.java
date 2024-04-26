package com.app.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.forum.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

}
