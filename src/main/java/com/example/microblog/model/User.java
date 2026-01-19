package com.example.microblog.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
public class User {

    public User(){}

    public User(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public User(String userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", unique = true, nullable = false)
    private String userId;

    @OneToMany(mappedBy = "followee", fetch = FetchType.LAZY)
    private List<Follow> followee;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    private List<Follow> follower;



}
