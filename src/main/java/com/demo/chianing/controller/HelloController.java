package com.demo.chianing.controller;

import com.demo.chianing.controller.system.BaseController;
import com.demo.chianing.system.http.ResBuilder;
import com.demo.chianing.system.http.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController extends BaseController {

    @GetMapping("/hello")
    public Response<String> hello(@RequestParam(name = "name", defaultValue = "Chianing") String name) {
        logger.info("hello, name is {}", name);
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
        return ResBuilder.success(String.format("hello, %s", name));
    }

}
