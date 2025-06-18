package com.kapil.howsyou.service;

import com.kapil.howsyou.entity.Post;
import com.kapil.howsyou.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
