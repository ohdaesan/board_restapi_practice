package com.ohdaesan.board.service;

import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohdaesan.board.global.PostNotFoundException;
import org.springframework.stereotype.Service;

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

//    @Override
//    public PostDTO getPostByPostId(long postId) {
//        return posts.stream()
//                .filter(post -> post.getPostId() == postId)
//                .toList().get(0);
//    }

    public Post getPostByPostId(long postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("값이 없어요~" + postId));
    }
}
