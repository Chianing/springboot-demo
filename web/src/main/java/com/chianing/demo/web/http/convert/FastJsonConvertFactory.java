package com.chianing.demo.web.http.convert;

import com.alibaba.fastjson2.JSON;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import org.springframework.context.annotation.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * FastJson适配retrofit的请求参数转换类
 *
 * @author chianing
 * @description FastJson适配retrofit的请求参数转换类
 * @date 2023/10/20 15:45
 */
@Configuration
public class FastJsonConvertFactory extends Converter.Factory {

    @Override
    @EverythingIsNonNull
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return (Converter<ResponseBody, Object>) value -> {
            try (BufferedSource bufferedSource = Okio.buffer(value.source())) {
                String tempStr = bufferedSource.readUtf8();
                return JSON.parseObject(tempStr, type);
            }
        };
    }

    @Override
    @EverythingIsNonNull
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return (Converter<Object, RequestBody>) value ->
                RequestBody.Companion.create(JSON.toJSONBytes(value),
                        MediaType.parse("application/json; charset=UTF-8"));
    }

}
