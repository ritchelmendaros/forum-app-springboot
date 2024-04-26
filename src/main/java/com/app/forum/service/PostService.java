package com.app.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import com.app.forum.model.Post;
import com.app.forum.repository.PostRepo;

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

    public List<Post> getPosts(int page) {
        int pageIndex = page > 0 ? page - 1 : 0;
        Page<Post> postPage = postRepository.findAll(PageRequest.of(pageIndex, 10));
        return postPage.getContent();
    }

    public boolean deletePost(Integer postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existsById(Integer postId) {
        return postRepository.existsById(postId);
    }

    public Post findById(Integer postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
