package com.ohdaesan.board.controller;

import com.ohdaesan.board.common.ResponseMsg;
import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.global.PostNotFoundException;
import com.ohdaesan.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Spring Boot Swagger 연동 API (Board)")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

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
    @ApiResponses({
            @ApiResponse(responseCode = "203", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")
    })
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPost(@PathVariable long postId, @RequestBody PostDTO modifiedPost) throws PostNotFoundException {
        postService.updatePost(postId, modifiedPost.getTitle(), modifiedPost.getContent());

        Map<String, Object> responseMap = new HashMap<>();
        String msg = "게시글 수정에 성공하였습니다.";
        responseMap.put("result", msg);

        return ResponseEntity.ok().body(new ResponseMsg(203, "게시글 수정 성공", responseMap));
    }



    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "특정 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")
    })
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost (@PathVariable long postId) throws PostNotFoundException{
        Map<String, Object> responseMap = new HashMap<>();

        boolean isDeleted = postService.deletePost(postId);
        if(isDeleted) {
            String msg = "게시글 삭제에 성공하였습니다.";
            responseMap.put("result", msg);
        } else {
            throw new PostNotFoundException("게시글 삭제에 실패하였습니다.");
        }

        return ResponseEntity
                .ok()
                .body(new ResponseMsg(204, "게시글 삭제 성공", responseMap));
    }
}