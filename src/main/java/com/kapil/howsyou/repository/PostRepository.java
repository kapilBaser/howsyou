package com.kapil.howsyou.repository;

import com.kapil.howsyou.entity.Comment;
import com.kapil.howsyou.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
