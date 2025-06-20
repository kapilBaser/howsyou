package com.kapil.howsyou.controller;

import com.kapil.howsyou.dto.PostDto;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.utils.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class IndexController {

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("/")
    public List<PostDto> getUserFeed(){
        HowsyouUser howsyouUser = authenticatedUser.getAuthenticatedUser();
        Set<HowsyouUser> following =  howsyouUser.getFollowing();
        List<PostDto> postDtos = new ArrayList<>();
        if(following.isEmpty()){
            return null;
        }
        following.forEach(followedUser -> {
            followedUser.getPosts().forEach(post -> {
                PostDto postDto = PostDto.mapToPostDto(post);
                postDtos.add(postDto);
            });
        });
        List<PostDto> sortedList = new ArrayList<>(postDtos.stream()
                .sorted(Comparator.comparing(PostDto::getCreatedAt))
                .toList());
        Collections.reverse(sortedList);
        return sortedList;
    }

    @GetMapping("/greet")
    public String greet(){
        return "Welcome to howsyou!!!";
    }

}
