package com.example.microblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import com.example.microblog.model.Follow;
import com.example.microblog.model.Post;
import com.example.microblog.model.User;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceIntegrationTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepo;

    @Autowired
    UserRepository userRepo;

    @BeforeEach
    public void populate(){
        Post post= new Post(1L, "alice", "my own post alice!", Instant.now());
        postRepo.save(post);
        User user = new User(1L, "alice");
        userRepo.save(user);

    }

    @Test
    public void testSavePostFollow() {
       
        String postId = "1";
        String followerId = "john";
        Post post = postService.savePostFollow(followerId, postId);
        List<Follow> followings = post.getFollowings();
        assertTrue(followings.size() > 0);

        for(Follow follow:followings) {
            assertEquals("alice", follow.getFollowee().getUserId());
            assertEquals("john", follow.getFollower().getUserId());
             System.out.println("followee: " + follow.getFollowee().getUserId() + ", followerId: " + follow.getFollower().getUserId());
        }
       
    }
    



}
