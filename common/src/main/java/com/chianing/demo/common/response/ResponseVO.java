package com.chianing.demo.common.response;

import com.chianing.demo.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求响应实体
 *
 * @author chianing
 * @description 请求响应实体
 * @date 2023/10/19 18:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class ResponseVO<T> {

    /**
     * 状态码
     *
     * @see ResponseCode#getCode()
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 错误信息
     *
     * @see ResponseCode#getErrMsg()
     */
    private String errMsg;

    public static <T> ResponseVO<T> succeed() {
        return build(ResponseCode.SUCCEED);
    }

    public static <T> ResponseVO<T> succeed(T data) {
        ResponseVO<T> result = build(ResponseCode.SUCCEED);
        result.setData(data);
        return result;
    }

    public static <T> ResponseVO<T> failed() {
        return build(ResponseCode.FAILED);
    }

    public static <T> ResponseVO<T> failed(String errMsg) {
        return build(ResponseCode.FAILED, errMsg);
    }

    public static <T> ResponseVO<T> errorParam() {
        return build(ResponseCode.ERROR_PARAM);
    }

    public static <T> ResponseVO<T> errorParam(String errMsg) {
        return build(ResponseCode.ERROR_PARAM, errMsg);
    }

    public static <T> ResponseVO<T> errorUnknown() {
        return build(ResponseCode.ERROR_UNKNOWN);
    }

    public static <T> ResponseVO<T> errorUnknown(String errMsg) {
        return build(ResponseCode.ERROR_UNKNOWN, errMsg);
    }

    public static <T> ResponseVO<T> errorOuter() {
        return build(ResponseCode.ERROR_OUTER);
    }

    public static <T> ResponseVO<T> errorOuter(String errMsg) {
        return build(ResponseCode.ERROR_OUTER, errMsg);
    }

    private static <T> ResponseVO<T> build(ResponseCode responseCode) {
        return ResponseVO.<T>builder()
                .code(responseCode.getCode())
                .errMsg(responseCode.getErrMsg())
                .data(null)
                .build();
    }

    private static <T> ResponseVO<T> build(ResponseCode responseCode, String errMsg) {
        ResponseVO<T> result = build(responseCode);
        result.setErrMsg(errMsg);
        return result;
    }

    public boolean isSucceed() {
        return ResponseCode.SUCCEED.getCode() == code;
    }

}
