package com.kapil.howsyou.service;

import com.kapil.howsyou.dto.PostDto;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.entity.Post;
import com.kapil.howsyou.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureAdapter;

import java.util.Collection;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Post findPostById(Long id){
        return postRepository.findById(id).isPresent() ? postRepository.findById(id).get() : null;
    }

    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }

    public Page<Post> getUserFeed(Collection<HowsyouUser> followedUsers, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return postRepository.findByAuthorIn(followedUsers, pageable);

    }




}
