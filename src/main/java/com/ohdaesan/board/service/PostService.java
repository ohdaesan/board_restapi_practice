package com.ohdaesan.board.service;

import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.global.PostNotFoundException;
import com.ohdaesan.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

//    private final List<PostDTO> posts;
    private final PostRepository postRepository;

//    @Autowired
//    public PostRepository(List<PostDTO> posts) {
//        this.posts = posts;
//    }

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    



//    @Override
//    public PostDTO getPostByPostId(long postId) {
//        return posts.stream()
//                .filter(post -> post.getPostId() == postId)
//                .toList().get(0);
//    }

    public PostDTO getPostByPostId(long postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() ->new PostNotFoundException("값이 없어요~" + postId));
    }
}
