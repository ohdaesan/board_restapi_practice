package com.ohdaesan.board.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {
    private int postId;
    private String title;
    private String content;
}