package com.example.microblog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microblog.model.Post;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private PostService postService;

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

    @PostMapping("/{postId}")
    public ResponseEntity<Post> addPostFollow(@PathVariable String postId, @RequestParam String followerId ) {
        Post post = postService.savePostFollow(followerId, postId);
        return ResponseEntity.ok(post);
    }
}
