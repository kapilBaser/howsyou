package com.kapil.howsyou.controller;

import com.kapil.howsyou.dto.Profile;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.entity.Post;
import com.kapil.howsyou.service.PostService;
import com.kapil.howsyou.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/profile/")
    public Profile getLoggedInUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return Profile.mapToProfile((HowsyouUser) userService.loadUserByUsername(username));
    }

    @PutMapping("/profile/")
    public Profile updateProfile(@RequestBody Profile profile){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        HowsyouUser howsyouUser = (HowsyouUser) userService.loadUserByUsername(username);
        howsyouUser.setName(profile.getName());
        howsyouUser.setBio(profile.getBio());
        howsyouUser.setEmail(profile.getEmail());
        userService.save(howsyouUser);
        return profile;
    }

    @GetMapping("/post")
    public List<Post> getPosts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HowsyouUser howsyouUser = (HowsyouUser) userService.loadUserByUsername(authentication.getName());
        return howsyouUser.getPosts();
    }

    @PostMapping("/post")
    public HowsyouUser addPost(@RequestBody Post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HowsyouUser howsyouUser = (HowsyouUser) userService.loadUserByUsername(authentication.getName());
        List<Post> userPosts = howsyouUser.getPosts();
        if(userPosts == null)
            userPosts = new ArrayList<>();
        post.setAuthor(howsyouUser);
        userPosts.add(post);
        howsyouUser.setPosts(userPosts);
        userService.save(howsyouUser);
        return howsyouUser;
    }

    @PutMapping("/post")
    public Post updatePost(@RequestParam long postId, @RequestBody Post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post savedPost = postService.findPostById(postId);
        if(savedPost != null && savedPost.getAuthor().getEmail().equals(authentication.getName())){
            savedPost.setContent(post.getContent());
            return postService.save(savedPost);
        }
        return null;

    }

    @DeleteMapping("/post")
    public void deletePost(@RequestParam long postId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post savedPost = postService.findPostById(postId);
        if(savedPost != null && savedPost.getAuthor().getEmail().equals(authentication.getName())){
            postService.deletePostById(postId);
        }
    }

}
