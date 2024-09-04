package com.ohdaesan.board.controller;

import com.ohdaesan.board.common.ResponseMsg;
import com.ohdaesan.board.domain.dto.PostDTO;
import com.ohdaesan.board.global.PostNotFoundException;
import com.ohdaesan.board.service.PostService;
import com.ohdaesan.board.domain.entity.Post;
import com.ohdaesan.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<ResponseMsg> createNewPost (@RequestBody PostDTO newPost) {

//        System.out.println("newPost = " + newPost);
        postService.registPost(newPost);

        String successMsg = "게시글 등록에 성공하였습니다.";

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", successMsg);

        return ResponseEntity
                .ok()
                .body(new ResponseMsg(201, "게시글 추가 성공", responseMap));
    }

    // 게시글 전체 조회
    @Operation(summary = "게시글 전체 조회", description = "사이트의 게시글 전체 조회")
    @GetMapping("/posts")
    public ResponseEntity<ResponseMsg> findAllPosts() {

        List<Post> posts = postService.findAllPosts();

        Map<String,Object> responseMap= new HashMap<>();
        responseMap.put("posts",posts);

        // 밑의 방법과 동일함.
//        ResponseMsg responseMsg = new ResponseMsg(
//                200,"조회성공",responseMap
//        );
//        return new ResponseEntity<>(responseMsg, HttpStatus.OK);

        return ResponseEntity.ok().body(new ResponseMsg(200,"조회성공",responseMap));
    }

    // 게시글 단일 조회
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

    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "특정 게시글 수정")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPost(@PathVariable int postId, @RequestBody PostDTO modifiedPost) {

        return null;
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
