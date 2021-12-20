package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;
}
