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
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower; 

    @Id
    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    private Post post;


    protected Follow() {}

    public Follow(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public void setPost(Post post) {
        this.post = post;
    }



    public User getFollower() { return follower; }
    public User getFollowee() { return followee; }

    public static class Key implements Serializable {
        //user follower
        public User follower;
        //owner of the blog 
        public User followee;

        public Key() {
            
        }

        public Key(User follower, User followee) {
            this.follower = follower;
            this.followee = followee;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if( o== null || getClass() != o.getClass()) return false;
            Key key = (Key)o;
            return follower.equals(key.followee) & followee.equals(key.followee);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(follower, followee);
        }
    }
}
