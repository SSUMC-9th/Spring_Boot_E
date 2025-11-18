package com.umc_study.mission_server.temp.service;

import com.umc_study.mission_server.temp.exception.TestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {
    @Override
    public void checkFlag(Long flag) {
        if (flag == 1) {
            throw new TestException();
        }
    }
}
