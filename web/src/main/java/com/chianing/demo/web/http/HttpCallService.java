package com.chianing.demo.web.http;

import com.chianing.demo.web.http.api.TestApi;
import com.chianing.demo.web.http.config.OuterApiProperties;
import com.chianing.demo.web.http.convert.FastJsonConvertFactory;
import jakarta.annotation.Resource;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

/**
 * http调用service
 *
 * @author chianing
 * @description http调用service
 * @date 2023/10/20 14:23
 */
@Component
public class HttpCallService {

    @Resource
    private OuterApiProperties outerApiProperties;

    @Resource
    private FastJsonConvertFactory fastJsonConvertFactory;
    @Resource
    @Qualifier("testApiOkHttpClient")
    private OkHttpClient testApiOkHttpClient;

    public TestApi getTestApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(outerApiProperties.getTestHost())
                .client(testApiOkHttpClient)
                .addConverterFactory(fastJsonConvertFactory)
                .build();

        return retrofit.create(TestApi.class);
    }

}
