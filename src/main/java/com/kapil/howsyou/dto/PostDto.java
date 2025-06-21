package com.kapil.howsyou.dto;

import com.kapil.howsyou.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDto {
    private String content;
    private String author;
    private Long likes = 0l;
    private List<CommentDto> comments;
    private LocalDateTime createdAt;

    public PostDto() {}

    public PostDto(String content, String author, long likes, LocalDateTime createdAt, List<CommentDto> comments) {
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.createdAt = createdAt;
        this.comments = comments;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public static PostDto mapToPostDto(Post post){
        List<CommentDto> comments = new ArrayList<>();
        post.getComments().forEach(comment -> comments.add(CommentDto.mapToCommentDto(comment)));
        return new PostDto(post.getContent(), post.getAuthor().getName(), post.getLikes(), post.getCreatedAt(), comments);
    }

    public static PostDto mapToPostDto(Post post, List<CommentDto> comments){
        return new PostDto(post.getContent(), post.getAuthor().getName(), post.getLikes(), post.getCreatedAt(), comments);
    }

}
