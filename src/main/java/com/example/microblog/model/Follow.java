package com.example.microblog.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Simple follower -> followee mapping.
 */
@Entity
@IdClass(Follow.Key.class)
public class Follow {

    @Id
    private String followerId;

    @Id
    private String followeeId;

    protected Follow() {}

    public Follow(String followerId, String followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public String getFollowerId() { return followerId; }
    public String getFolloweeId() { return followeeId; }

    public static class Key implements Serializable {
        public String followerId;
        public String followeeId;
    }
}
