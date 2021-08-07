package com.demo.chianing.system.handler;

import com.demo.chianing.system.http.ResBuilder;
import com.demo.chianing.system.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller异常处理类
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Response<?> dealExceptionResult(Throwable throwable) {
        log.error("catch throwable: ", throwable);
        return ResBuilder.serverError();
    }

}
