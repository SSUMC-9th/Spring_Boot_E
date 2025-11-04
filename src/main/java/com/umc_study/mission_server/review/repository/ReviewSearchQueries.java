package com.umc_study.mission_server.review.repository;

import com.umc_study.mission_server.common.Range;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewSearchQueries {
    public enum ReviewSearchOrderMode {
        LATEST,
        NAME
    }

    private Long memberId;

    @Builder.Default
    private List<String> storeNames = Collections.emptyList();

    @Builder.Default
    private List<String> regionNames = Collections.emptyList();
    private Range scoreRange;

    @Builder.Default
    private ReviewSearchOrderMode orderMode = ReviewSearchOrderMode.LATEST;

    private Integer page;
    private Integer size;
}
