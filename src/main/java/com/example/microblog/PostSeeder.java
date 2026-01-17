package com.example.microblog;

import com.example.microblog.model.Post;
import com.example.microblog.repository.PostRepository;
import java.time.Instant;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostSeeder {

    private final PostRepository postRepository;

    public PostSeeder(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {
        List<Post> posts = List.of(
                new Post(1L, "alice", "First post alice!", Instant.now()),
                new Post(2L, "bob", "Second post bob!", Instant.now()),
                new Post(3L, "charlie", "Post from charlie!", Instant.now()),
                new Post(4L, "alice", "Another post from alice!", Instant.now())
        );
      postRepository.saveAll(posts);
    }
}