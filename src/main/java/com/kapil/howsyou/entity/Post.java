package com.kapil.howsyou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private long likes = 0l;

    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonIgnore
    private HowsyouUser author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public HowsyouUser getAuthor() {
        return author;
    }

    public void setAuthor(HowsyouUser author) {
        this.author = author;
    }
}
