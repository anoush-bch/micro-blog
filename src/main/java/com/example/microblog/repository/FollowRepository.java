package com.example.microblog.repository;

import com.example.microblog.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FollowRepository extends JpaRepository<Follow, Follow.Key> {

}
