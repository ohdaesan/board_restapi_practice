package com.ohdaesan.board.controller;

import com.ohdaesan.board.common.ResponseMsg;
import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.global.PostNotFoundException;
import com.ohdaesan.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Spring Boot Swagger 연동 API (Board)")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class PostController {
    private final PostService postService;


    @Operation(
            summary = "게시글 번호로 특정 게시글 조회",
            description = "게시글 번호를 통해 특정 게시글을 조회한다.",
            parameters = {
                    @Parameter(
                            name = "postId",
                            description = "사용자 화면에서 넘어오는 post의 pk"
                    )
            })
    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponseMsg> findPostByPostId(@PathVariable long postId) throws PostNotFoundException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );



        PostDTO foundPost = postService.getPostByPostId(postId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", foundPost);


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMsg(200, "단일조회성공", responseMap));

        //        PostDTO foundPost = posts.stream()
//                .filter(post -> post.getPostId() ==postId).toList().get(0);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("post", foundPost);


    }




}
