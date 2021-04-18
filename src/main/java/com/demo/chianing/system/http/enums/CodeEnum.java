package com.demo.chianing.system.http.enums;

import com.demo.chianing.system.Constants;

public enum CodeEnum {
    SUCCESS(200, "成功", Constants.Status.SUCCESS, "请求成功"),

    DATA_NOT_FOUND(404, "数据不存在", Constants.Status.FAILURE, "请求的数据不存在"),

    PARAM_LOST(430, "参数缺失", Constants.Status.FAILURE, "请求参数不足"),
    PARAM_ILLEGAL(431, "参数不合法", Constants.Status.FAILURE, "请求参数不合法"),

    ACCESS_DENIED(450, "无权访问", Constants.Status.FAILURE, "无权访问接口"),

    SERVER_ERROR(500, "服务端异常", Constants.Status.FAILURE, "服务端异常");

    private final Integer code;
    private final String desc;
    private final String status;
    private final String msg;

    CodeEnum(Integer code, String desc, String status, String msg) {
        this.code = code;
        this.desc = desc;
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        if (Constants.Status.FAILURE.equals(status)) {
            return "请求失败, " + msg;
        }
        return msg;
    }
}
