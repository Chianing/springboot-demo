package com.chianing.demo.web.service.impl;

import com.chianing.demo.common.util.CheckEmptyUtil;
import com.chianing.demo.web.http.HttpCallService;
import com.chianing.demo.web.http.api.response.TestApiResVO;
import com.chianing.demo.web.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * TestServiceImpl
 *
 * @author chianing
 * @description TestServiceImpl
 * @date 2023/10/20 14:43
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private HttpCallService httpCallService;

    @Override
    public String getRandomSentences() {
        TestApiResVO<TestApiResVO.Sentences> executeResult;
        try {
            executeResult = httpCallService.getTestApiCall()
                    .queryRandomSentences()
                    .execute()
                    .body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (CheckEmptyUtil.isEmpty(executeResult)) {
            throw new RuntimeException("result is empty");
        }
        if (!executeResult.isSucceed()) {
            throw new RuntimeException(executeResult.getMessage());
        }

        return executeResult.getResult().getName();
    }

}
