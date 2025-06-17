package com.kapil.howsyou.service;

import com.kapil.howsyou.entity.Comment;
import com.kapil.howsyou.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

    public List<Comment> findByPostIdAndCommentIsNull(Long postId) {
        return commentRepository.findByPostIdAndCommentIsNull(postId);
    }


}
