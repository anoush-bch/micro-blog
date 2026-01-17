package com.example.microblog.repository;

import com.example.microblog.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, String> {
}
