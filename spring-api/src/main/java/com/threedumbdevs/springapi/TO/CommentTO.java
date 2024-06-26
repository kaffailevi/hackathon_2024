package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommentTO implements Serializable {
    private Long id;
    private String answer;
    private Long userId;
    private Long postId;
    private String date;
    private String username;
}
