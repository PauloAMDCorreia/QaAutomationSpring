package com.example.demo.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPayload {
    private String userName;
    private String password;
}

