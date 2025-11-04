package com.umc_study.mission_server.review.dto;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSearchRequest {
    private Long memberId;
    private List<String> storeNames = Collections.emptyList();
    private List<String> regionNames = Collections.emptyList();
    private Integer score;
    private String orderMode;
    private Integer page;
    private Integer size;
}
