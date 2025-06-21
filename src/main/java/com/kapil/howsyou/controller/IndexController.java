package com.kapil.howsyou.controller;

import com.kapil.howsyou.dto.PostDto;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.entity.Post;
import com.kapil.howsyou.service.PostService;
import com.kapil.howsyou.service.UserService;
import com.kapil.howsyou.utils.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class IndexController {

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Page<PostDto> getUserFeed(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size){
        HowsyouUser howsyouUser = authenticatedUser.getAuthenticatedUser();
        Set<HowsyouUser> followedUsers = howsyouUser.getFollowing();
        Page<Post> postPage = postService.getUserFeed(followedUsers , page, size);
        return postPage.map(PostDto::mapToPostDto);
    }

    @GetMapping("/greet")
    public String greet(){
        return "Welcome to howsyou!!!";
    }

}
