package com.ohdaesan.board.controller;

import com.ohdaesan.board.domain.dto.PostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Spring Boot Swagger 연동 API (Board)")
@RestController
@RequestMapping("/board")
public class PostController {
    private List<PostDTO> posts;

    public PostController() {
        posts = new ArrayList<>();

        posts.add(new PostDTO(1, "title01", "content01"));
        posts.add(new PostDTO(2, "title02", "content02"));
        posts.add(new PostDTO(3, "title03", "content03"));
    }

    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "게시판에 업로드할 새로운 게시글 작성")
    @PostMapping("/posts")
    public ResponseEntity<?> createNewPost (@RequestBody PostDTO newPost) {

        return null;
    }

    // 게시글 전체 조회
    @Operation(summary = "게시글 전체 조회", description = "사이트의 게시글 전체 조회")
    @GetMapping("/posts")
    public ResponseEntity<?> findAllPosts() {

        return null;
    }

    // 게시글 단일 조회
    @Operation(summary = "게시글 번호로 특정 게시글 조회",
            description = "게시글 번호를 통해 특정 게시글을 조회한다.",
            parameters = {@Parameter(name = "postId", description = "사용자 화면에서 넘어오는 post의 pk")})
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> findPostByPostId(@PathVariable int postId) {

        return null;
    }

    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "특정 게시글 수정")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPost(@PathVariable long postId, @RequestBody PostDTO modifiedPost) {

        PostDTO foundPost = posts.stream().filter(user -> user.getPostId() == postId).toList().get(0);

        foundPost.setPostId(modifiedPost.getPostId());
        foundPost.setTitle(modifiedPost.getTitle());
        foundPost.setContent(modifiedPost.getContent());

        return ResponseEntity.created(URI.create("/entity/posts/" + postId)).build();
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "특정 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")
    })
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost (@PathVariable int postId) {

        return null;
    }
}
