package com.app.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.forum.model.Post;
import com.app.forum.repository.PostRepo;
import com.app.forum.request.AddNewPost;

@Service
public class PostService {

    private final PostRepo postRepository;

    @Autowired
    public PostService(PostRepo postRepository) {
        this.postRepository = postRepository;
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

}
