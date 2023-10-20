package com.chianing.demo.web.controller;

import com.chianing.demo.common.response.ResponseVO;
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
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public ResponseVO<String> hello() {
        return ResponseVO.succeed("hello");
    }

}
