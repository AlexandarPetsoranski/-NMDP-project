package com.example.demo.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostObject {
    int USER_ID;
    int ID;
    String TITLE;
    Boolean BODY;

}
