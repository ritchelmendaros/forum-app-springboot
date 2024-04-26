package com.app.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.forum.model.Reply;
import com.app.forum.repository.ReplyRepo;

@Service
public class ReplyService {
    private final ReplyRepo replyRepo;

    @Autowired
    public ReplyService(ReplyRepo replyRepo) {
        this.replyRepo = replyRepo;
    }

    public boolean save(Reply reply) {
        try {
            replyRepo.save(reply);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReply(Integer replyId) {
        try {
            replyRepo.deleteById(replyId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
