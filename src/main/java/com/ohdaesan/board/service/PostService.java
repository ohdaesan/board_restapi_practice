package com.ohdaesan.board.service;

import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.global.PostNotFoundException;
import com.ohdaesan.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import com.ohdaesan.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public boolean editPost(long postId, Post modifiedPost) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isPresent()) {
                Post existingPost = optionalPost.get();
                // Reflection API를 사용하여 필드 값을 직접 변경
                existingPost.getClass().getDeclaredField("title").set(existingPost, modifiedPost.getTitle());
                existingPost.getClass().getDeclaredField("content").set(existingPost, modifiedPost.getContent());

                postRepository.save(existingPost);
                log.info("게시글이 성공적으로 수정되었습니다. ID: {}", postId);
                return true;
            } else {
                log.warn("게시글을 찾을 수 없습니다. ID: {}", postId);
                return false;
            }
        } catch (Exception e) {
            log.error("게시글 수정 중 오류 발생. ID: {}", postId, e);
            return false;
        }
    }

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
}
