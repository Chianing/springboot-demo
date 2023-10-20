package com.chianing.demo.web.controller;

import com.chianing.demo.common.response.ResponseVO;
import com.chianing.demo.web.service.TestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author chianing
 * @description TestController
 * @date 2023/10/19 17:48
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public ResponseVO<String> hello() {
        return ResponseVO.succeed("hello");
    }

    @GetMapping("/randomSentences")
    public ResponseVO<String> getRandomSentences() {
        try {
            return ResponseVO.succeed(testService.getRandomSentences());
        } catch (Exception e) {
            log.error("get random sentences error", e);
            return ResponseVO.failed(e.getMessage());
        }
    }

}
