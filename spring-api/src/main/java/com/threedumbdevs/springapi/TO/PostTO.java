package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PostTO implements Serializable {
    private Long id;
    private String description;
    private Long userId;
    private String date;
    private String imageUrl;
}
