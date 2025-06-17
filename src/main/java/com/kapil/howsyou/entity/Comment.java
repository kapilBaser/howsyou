package com.kapil.howsyou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    private HowsyouUser author;

    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonIgnore
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Comment> commentReplies = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Comment comment;

    public List<Comment> getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(List<Comment> commentReplies) {
        this.commentReplies = commentReplies;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HowsyouUser getAuthor() {
        return author;
    }

    public void setAuthor(HowsyouUser author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
