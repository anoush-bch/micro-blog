package com.example.microblog;

import java.time.Instant;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.microblog.model.Post;
import com.example.microblog.model.User;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

@Component
public class PostSeeder {

    private final PostRepository postRepository;

    private final UserRepository userRepo;

    public PostSeeder(PostRepository postRepository, UserRepository userRepo) {
        this.postRepository = postRepository;
        this.userRepo = userRepo;
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

      //seed users
       List<User> users = List.of(
                new User(1L, "alice"),
                new User(2L, "bob"),
                new User(3L, "charlie"));

       userRepo.saveAll(users); 
    
    }
}