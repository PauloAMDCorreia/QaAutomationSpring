package com.example.demo.payloads;

import lombok.Data;
import java.util.List;

@Data
public class BookListResponse {
    private List<Book> books;
}

