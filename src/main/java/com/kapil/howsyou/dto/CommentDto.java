package com.kapil.howsyou.dto;

import com.kapil.howsyou.entity.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class CommentDto {
    private String content;
    private String author;
    private List<CommentDto> commentReplies;

    public CommentDto(){

    }

    public CommentDto(String content, String author, List<CommentDto> commentReplies) {
        this.content = content;
        this.author = author;
        this.commentReplies = commentReplies;
    }

    public CommentDto(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public List<CommentDto> getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(List<CommentDto> commentReplies) {
        this.commentReplies = commentReplies;
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

    public static CommentDto mapToCommentDto(Comment comment){
        List<CommentDto> replyDtos = comment.getCommentReplies().stream()
                .map(CommentDto::mapToCommentDto)
                .collect(Collectors.toList());
        return new CommentDto(comment.getContent(), comment.getAuthor().getName(), replyDtos);
    }

}
