package com.ohdaesan.board.repository;

import com.ohdaesan.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ohdaesan.board.service.PostService;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post getPostByPostId(long postId);
}
