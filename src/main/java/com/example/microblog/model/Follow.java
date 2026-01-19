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
    //@MapsId("followerId")
    @JoinColumn(name = "follower_id", foreignKey = @ForeignKey(name = "flwr_user"))
    private User follower; 

    @Id
    @ManyToOne
   // @MapsId("followeeId")
    @JoinColumn(name = "followee_id", foreignKey = @ForeignKey(name = "flwee_user"))
    private User followee;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "follow_post"))
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
        //many to many
        public User follower;
        //owner of the blog - many to one
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
