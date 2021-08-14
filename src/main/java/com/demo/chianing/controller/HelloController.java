package com.demo.chianing.controller;

import com.demo.chianing.system.http.Response;
import com.demo.chianing.system.properties.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController extends BaseController {

    @GetMapping("/hello")
    public Response<String> hello(@RequestParam(name = "name", defaultValue = "Chianing") String name) {
        log.info("hello, name is {}, active env is: {}", name, Properties.system.getActiveEnv());
        return success(String.format("hello, %s", name));
    }

}
