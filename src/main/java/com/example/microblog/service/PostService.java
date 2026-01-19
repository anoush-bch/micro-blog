package com.example.microblog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microblog.model.Follow;
import com.example.microblog.model.Post;
import com.example.microblog.model.User;
import com.example.microblog.repository.FollowRepository;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    FollowRepository followRepo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    UserRepository userRepo;

    //create Follow
    @Transactional
    public Post savePostFollow(String followerId, String postId){

        
         Post post = postRepo.findById(Long.valueOf(postId)).orElseThrow(()->
            new EntityNotFoundException("--post not found with id " + postId));

      
         //lookup followee/author by post's userId, throw exception if not found. It should be found assuming that post has a user
        User followeeUser = userRepo.findByUserId(post.getUserId()).orElseThrow(()->
           new EntityNotFoundException("--user not found user id " + post.getUserId()));

         //lookup follower by userId passed, create new user if it does not exist
        User followerUser = userRepo.findByUserId(followerId).orElseGet(() -> {
            User u = new User(followerId);
            return u;
        });
        
        userRepo.saveAndFlush(followerUser);
        
        Follow follow = new Follow(followerUser, followeeUser);
        follow.setPost(post);
        followRepo.saveAndFlush(follow);

        return post;
    }

}
