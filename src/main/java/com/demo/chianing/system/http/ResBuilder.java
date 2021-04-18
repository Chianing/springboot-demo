package com.demo.chianing.system.http;

import com.demo.chianing.system.http.enums.CodeEnum;
import com.demo.chianing.util.StringUtil;

public class ResBuilder {

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return buildSuccessResult(data);
    }

    public static <T> Response<T> dataNotFound() {
        return dataNotFound(null);
    }

    public static <T> Response<T> dataNotFound(String errMsg) {
        return buildFailureResult(CodeEnum.DATA_NOT_FOUND, errMsg);
    }

    public static <T> Response<T> paramLost() {
        return paramLost(null);
    }

    public static <T> Response<T> paramLost(String errMsg) {
        return buildFailureResult(CodeEnum.PARAM_LOST, errMsg);
    }

    public static <T> Response<T> paramIllegal() {
        return paramIllegal(null);
    }

    public static <T> Response<T> paramIllegal(String errMsg) {
        return buildFailureResult(CodeEnum.PARAM_ILLEGAL, errMsg);
    }

    public static <T> Response<T> accessDenied() {
        return accessDenied(null);
    }

    public static <T> Response<T> accessDenied(String errMsg) {
        return buildFailureResult(CodeEnum.ACCESS_DENIED, errMsg);
    }

    public static <T> Response<T> serverError() {
        return serverError(null);
    }

    public static <T> Response<T> serverError(String errMsg) {
        return buildFailureResult(CodeEnum.SERVER_ERROR, errMsg);
    }

    public static <T> Response<T> buildCustom(Integer code, String errMsg, String status, T data) {
        return new Response<>(code, errMsg, status, data);
    }

    private static <T> Response<T> buildSuccessResult(T data) {
        return buildResult(CodeEnum.SUCCESS, null, data);
    }

    private static <T> Response<T> buildFailureResult(CodeEnum codeEnum, String errMsg) {
        return buildResult(codeEnum, errMsg, null);
    }

    private static <T> Response<T> buildResult(CodeEnum codeEnum, String errMsg, T data) {
        return buildCustom(
                codeEnum.getCode(),
                (!CodeEnum.SUCCESS.equals(codeEnum) && StringUtil.isBlank(errMsg)) ? codeEnum.getMsg() : errMsg,
                codeEnum.getStatus(),
                data
        );
    }

}