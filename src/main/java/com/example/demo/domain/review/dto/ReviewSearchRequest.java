package com.example.demo.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchRequest {
    private String storeName;
    private Double star;
}
