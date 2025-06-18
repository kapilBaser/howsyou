package com.kapil.howsyou.dto;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.dto.PostDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Profile {
    private String name;
    private String email;
    private String bio = "";
    private List<PostDto> posts;

    private Set<Profile> following;

    private Set<Profile> followers;

    public Profile(){}


    public Profile(String name, String email, String bio, List<PostDto> posts) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }
    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Set<Profile> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Profile> following) {
        this.following = following;
    }

    public Set<Profile> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Profile> followers) {
        this.followers = followers;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
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
        List<PostDto> postDtos = new ArrayList<>();
        howsyouUser.getPosts().forEach(post -> {
            List<CommentDto> comments = new ArrayList<>();
            post.getComments().forEach(comment -> {
                comments.add(CommentDto.mapToCommentDto(comment));
            });

            postDtos.add(PostDto.mapToPostDto(post, comments));
        });

        Set<Profile> followings = new HashSet<>();
        howsyouUser.getFollowing().forEach(user -> followings.add(new Profile(user.getName(), user.getEmail())));
        Set<Profile> followers = new HashSet<>();
        howsyouUser.getFollowers().forEach(user -> followers.add(new Profile(user.getName(), user.getEmail())));
        Profile dto = new Profile(howsyouUser.getName(), howsyouUser.getEmail(), howsyouUser.getBio(), postDtos);
        dto.setFollowing(followings);
        dto.setFollowers(followers);
        return dto;
    }

}
