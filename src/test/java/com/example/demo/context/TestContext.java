package com.example.demo.context;

import lombok.Data;
import java.util.List;

@Data
public class TestContext {
    private String username;
    private String password;
    private String userId;
    private String token;
    private List<String> bookIsbns;
    private List<String> chosenBooks;
}