package com.example.microblog.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// User ID of the author
    private String userId;

    /// Content of the post, limited to 280 characters

    @Column(length = 280)
    private String content;

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
}
