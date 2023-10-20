package com.chianing.demo.web.http.response;

import lombok.Data;

import java.util.Objects;

/**
 * TestApi请求响应实体
 *
 * @author chianing
 * @description TestApi请求响应实体
 * @date 2023/10/20 14:12
 * @see com.chianing.demo.web.http.api.TestApi
 */
@Data
public class TestApiResVO<T> {
    private Integer code;
    private String message;
    private T result;

    public boolean isSucceed() {
        return Objects.equals(code, 200);
    }

    @Data
    public static class Sentences {
        private String name;
        private String from;
    }
}
