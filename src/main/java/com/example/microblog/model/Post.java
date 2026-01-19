package com.example.microblog.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// User ID of the author
    @Column
    private String userId;

    /// Content of the post, limited to 280 characters
    @Column(length = 280)
    private String content;

    //list of followings of the post, key-value pairs as followeeId-followerId
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Follow> followings;


    private Instant createdAt;

    protected Post() {}

    public Post(Long id, String userId, String content, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }

    public List<Follow> getFollowings(){
        return this.followings;
    }
    public void setFollowings(List<Follow> follows) {
        this.followings = follows;
    }
}
