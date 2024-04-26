package com.app.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.forum.model.Reply;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Integer> {

}
