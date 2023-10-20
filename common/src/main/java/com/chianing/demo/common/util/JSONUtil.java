package com.chianing.demo.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * json工具类
 *
 * @author chianing
 * @description json工具类
 * @date 2023/10/20 09:31
 */
@SuppressWarnings("unused")
public class JSONUtil {

    /**
     * 对象序列化为json字符串
     *
     * @param obj 目标对象
     * @return json字符串
     */
    public static String convert2JSONString(Object obj) {
        if (CheckEmptyUtil.isEmpty(obj)) {
            return "";
        }

        return JSON.toJSONString(obj);
    }

    /**
     * json字符串反序列化为对象
     *
     * @param jsonString json字符串
     * @param clazz      目标类型
     * @param <T>        目标对象类型
     * @return 目标对象
     */
    public static <T> T convert2Object(String jsonString, Class<T> clazz) {
        if (CheckEmptyUtil.isEmpty(jsonString)) {
            return null;
        }

        return JSONObject.parseObject(jsonString, clazz);

    }

    /**
     * json字符串反序列化为对象列表
     *
     * @param jsonString json字符串
     * @param clazz      目标类型
     * @param <T>        目标对象类型
     * @return 对象列表
     */
    public static <T> List<T> convert2Objects(String jsonString, Class<T> clazz) {
        if (CheckEmptyUtil.isEmpty(jsonString)) {
            return Collections.emptyList();
        }

        return JSONArray.parseArray(jsonString, clazz);
    }

}
