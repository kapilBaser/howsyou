package com.kapil.howsyou.dto;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.entity.Post;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class Profile {
    private String name;
    private String email;
    private String bio;
    private List<Post> posts;

    public Profile(){}


    public Profile(String name, String email, String bio, List<Post> posts) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static Profile mapToProfile(HowsyouUser howsyouUser){
        return new Profile(howsyouUser.getName(), howsyouUser.getEmail(), howsyouUser.getBio(), howsyouUser.getPosts());
    }

}
