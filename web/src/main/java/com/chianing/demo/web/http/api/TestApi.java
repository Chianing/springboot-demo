package com.chianing.demo.web.http.api;

import com.chianing.demo.web.http.response.TestApiResVO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * TestApi
 *
 * @author chianing
 * @description TestApi
 * @date 2023/10/20 14:11
 */
public interface TestApi {
    /**
     * 随机获取一句诗词
     *
     * @return 诗词
     */
    @GET("/api/sentences")
    Call<TestApiResVO<TestApiResVO.Sentences>> queryRandomSentences();
}
