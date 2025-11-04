package com.umc_study.mission_server.review.dto;

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
    private String storeNameQuery;
    private List<String> regionNames;
    private Integer score;
    private String orderMode;
    private Integer page;
    private Integer size;
}
