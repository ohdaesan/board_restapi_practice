package com.ohdaesan.board.service;

import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.repository.PostRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void registPost(PostDTO newPost) {
        Post post = Post.builder()
                .postId(newPost.getPostId())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .build();

        postRepository.save(post);
    }

    public PostDTO getPostByPostId(long postId) throws PostNotFoundException {
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("값이 없어요~" + postId));

        return PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();






    }

    // 게시글 수정
    @Transactional
    public void updatePost(long postId, String title, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글을 찾을 수 없음"));

        // 찾은거 수정
        post.setTitle(title);
        post.setContent(content);

        // 저장
        postRepository.save(post);
    }
}
