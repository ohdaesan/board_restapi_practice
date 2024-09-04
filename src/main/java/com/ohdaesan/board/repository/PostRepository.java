package com.ohdaesan.board.repository;

import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.service.PostService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostDTO, Long> {

    PostDTO getPostByPostId(long postId);


}
