package com.example.microblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microblog.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

}

