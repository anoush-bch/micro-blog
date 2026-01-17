package com.example.microblog.api;

import com.example.microblog.model.Post;
import com.example.microblog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Return paginated posts for a given user
     */
    @GetMapping("/{userId}")
    public Page<Post> list(@PathVariable String userId, @RequestParam(defaultValue = "0") int page) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId, PageRequest.of(page, 10));
    }
}
