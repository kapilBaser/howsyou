package com.kapil.howsyou.dto;

import com.kapil.howsyou.entity.Comment;

public class CommentDto {
    private String content;
    private String author;

    public CommentDto(){

    }

    public CommentDto(String content, String author) {
        this.content = content;
        this.author = author;
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
        return new CommentDto(comment.getContent(), comment.getAuthor().getName());
    }

}
