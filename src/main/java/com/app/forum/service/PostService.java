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
        // Calculate the page index based on the requested page number
        int pageIndex = page > 0 ? page - 1 : 0;
        // Fetch posts for the specified page using PageRequest
        Page<Post> postPage = postRepository.findAll(PageRequest.of(pageIndex, 10)); // Assuming pageSize is 10
        // Extract the content (posts) from the page
        return postPage.getContent();
    }
}
