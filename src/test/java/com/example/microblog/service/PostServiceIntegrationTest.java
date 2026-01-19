package com.example.microblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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



    
    public Long populatePost(String userId){
        User user = new User(userId);
        userRepo.saveAndFlush(user);
        Post post= new Post(9L, "mike", "my own post mike!", Instant.now());
        post = postRepo.saveAndFlush(post);
        //id I passed gets ignored, doh
        return post.getId();
    }

    @Test
    public void testSavePostFollow_existingPost() {

        Long postId = populatePost("mike");
        String followerId = "john";
      
        Post post = postService.savePostFollow(followerId, postId.toString());
        List<Follow> followings = post.getFollowings();
        assertTrue(followings.size() > 0);

        for(Follow follow:followings) {
            assertEquals("mike", follow.getFollowee().getUserId());
            assertEquals("john", follow.getFollower().getUserId());
             System.out.println("followee: " + follow.getFollowee().getUserId() + ", followerId: " + follow.getFollower().getUserId()); 
        }
    }
   }
