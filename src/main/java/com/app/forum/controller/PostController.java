package com.app.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.app.forum.model.Post;
import com.app.forum.model.User;
import com.app.forum.request.AddNewPost;
import com.app.forum.service.PostService;
import com.app.forum.service.UserService;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<?> addPost(@RequestBody AddNewPost request) {
        try {
            User user = userService.findById(request.getUserid());

            Post post = new Post();
            post.setPost(request.getPost());
            post.setUser(user);

            postService.add(post);

            return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create post.");
        }
    }

    @GetMapping("/getposts")
    public ResponseEntity<?> getPosts(@RequestParam(name = "page") int page) {
        try {
            List<Post> posts = postService.getPosts(page);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve posts.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestParam(name = "postId") Integer postId) {
        try {
            boolean success = postService.deletePost(postId);
            if (success) {
                return ResponseEntity.ok("Post deleted successfully.");
            } else {
                return ResponseEntity.ok("Failed to delete post.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete post: " + e.getMessage());
        }
    }
}
