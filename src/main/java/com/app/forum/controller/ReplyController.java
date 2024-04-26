package com.app.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.forum.ReplyRequestDTO;
import com.app.forum.model.Reply;
import com.app.forum.service.PostService;
import com.app.forum.service.ReplyService;
import com.app.forum.service.UserService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public ReplyController(ReplyService replyService, UserService userService, PostService postService) {
        this.replyService = replyService;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/replytopost")
    public ResponseEntity<?> replyToPost(@RequestBody ReplyRequestDTO request) {
        try {
            // Check if user exists
            if (!userService.existsById(request.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User with id " + request.getUserId() + " does not exist.");
            }
            // Check if post exists
            if (!postService.existsById(request.getPostId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Post with id " + request.getPostId() + " does not exist.");
            }
            // Create the reply
            Reply reply = new Reply();
            reply.setUser(userService.findById(request.getUserId()));
            reply.setPost(postService.findById(request.getPostId()));
            reply.setReplytext(request.getReply());

            boolean success = replyService.save(reply);
            if (success) {
                return ResponseEntity.ok("Reply added successfully.");
            } else {
                return ResponseEntity.ok("Failed to add reply.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add reply: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletereply")
    public ResponseEntity<?> deleteReply(@RequestParam(name = "replyId") Integer replyId) {
        try {
            boolean success = replyService.deleteReply(replyId);
            if (success) {
                return ResponseEntity.ok("Reply deleted successfully.");
            } else {
                return ResponseEntity.ok("Failed to delete reply.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete reply: " + e.getMessage());
        }
    }
}
