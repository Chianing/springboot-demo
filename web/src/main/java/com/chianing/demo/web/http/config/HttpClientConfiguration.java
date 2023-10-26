package com.chianing.demo.web.http.config;

import com.chianing.demo.web.http.interceptor.ClientRequestLogInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * http客户端配置类
 *
 * @author chianing
 * @description http客户端配置类
 * @date 2023/10/20 14:27
 */
@Configuration
public class HttpClientConfiguration {

    /**
     * 默认okhttp客户端
     *
     * @return okhttp客户端
     */
    @Bean(name = "defaultOkHttpClient")
    public OkHttpClient defaultOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ClientRequestLogInterceptor("default", true));
        builder.setConnectionPool$okhttp(new ConnectionPool(64, 5, TimeUnit.MINUTES));
        return builder.build();
    }

    /**
     * testApi okhttp客户端
     *
     * @return testApi okhttp客户端
     */
    @Bean(name = "testApiOkHttpClient")
    public OkHttpClient testApiOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new ClientRequestLogInterceptor("testApi", true));
        builder.setConnectionPool$okhttp(new ConnectionPool(3, 1, TimeUnit.MINUTES));
        return builder.build();
    }

}
