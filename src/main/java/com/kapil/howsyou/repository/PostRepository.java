package com.kapil.howsyou.repository;

import com.kapil.howsyou.entity.Comment;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByAuthorIn(Collection<HowsyouUser> followedUsers, Pageable pageable);
}
