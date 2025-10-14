package com.example.demo.payloads;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class BookCollectionPayload {
    private String userId;
    private List<Map<String, String>> collectionOfIsbns;
}
