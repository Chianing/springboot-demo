package com.demo.chianing.system.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private Integer code;

    private String msg;

    private String status;

    private T data;
}
