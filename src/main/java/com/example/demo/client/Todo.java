package com.example.demo.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Integer user_Id;
    private Integer id;
    private String title;
    private Boolean completed;
}
