package com.ohdaesan.board.service;

import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import com.ohdaesan.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public boolean deletePost(long postNo) {
        try {
            if (postRepository.existsById(postNo)) {
                postRepository.deleteById(postNo);
                return true; // 게시글 삭제 성공
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    public void registPost(PostDTO newPost) {

        Post post = Post.builder()
                .postId(newPost.getPostId())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .build();

        postRepository.save(post);


    }
}
