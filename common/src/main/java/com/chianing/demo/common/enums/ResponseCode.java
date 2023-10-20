package com.chianing.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求响应码
 *
 * @author chianing
 * @description 请求响应码
 * @date 2023/10/19 18:09
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCEED(200, ""),

    WARNING(1001, "warning"),

    FAILED(2000, "failed"),

    ERROR_UNKNOWN(2100, "unknown error"),
    ERROR_PARAM(2101, "param error");

    private final int code;
    private final String errMsg;
}
