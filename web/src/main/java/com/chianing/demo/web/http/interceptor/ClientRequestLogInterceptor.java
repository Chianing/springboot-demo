package com.chianing.demo.web.http.interceptor;

import com.chianing.demo.common.util.CheckEmptyUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 客户端请求日志拦截器
 *
 * @author chianing
 * @description 客户端请求日志拦截器
 * @date 2023/10/20 16:51
 */
@Slf4j
@SuppressWarnings("unused")
public class ClientRequestLogInterceptor implements Interceptor {

    private final String serviceName;

    private final boolean truncateResponseOutput;

    private int outputStringMaxLen = 64;

    public ClientRequestLogInterceptor(String serviceName, boolean truncateResponseOutput) {
        this.serviceName = serviceName;
        this.truncateResponseOutput = truncateResponseOutput;
    }

    public ClientRequestLogInterceptor(String serviceName, int outputStringMaxLen) {
        this.serviceName = serviceName;
        this.truncateResponseOutput = true;
        this.outputStringMaxLen = outputStringMaxLen;
    }

    @Override
    public Response intercept(@NotNull Chain chain) {
        long startTime = System.currentTimeMillis();

        Request request = chain.request();
        Response response;
        String responseStr = "";
        String requestParamOutput = "";

        try {
            response = chain.proceed(chain.request());
            if (response.body() == null) {
                return response;
            }

            MediaType mediaType = response.body().contentType();
            responseStr = response.body().string();

            Response.Builder builder = response.newBuilder();
            builder.setBody$okhttp(ResponseBody.create(responseStr, mediaType));
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long endTime = System.currentTimeMillis();
            if (request.body() != null) {
                requestParamOutput = getRequestParamOutput(request.body(), serviceName);
            }

            log.info("""

                            -----Http Request Info-----
                            client name: {}, request url: {}, time cost: {}ms, headers: {}, requestParams: {}, response: {}
                            -----Request Finished------""",
                    serviceName, request.url(), (endTime - startTime), request.headers(),
                    requestParamOutput, truncateOutputStr(responseStr));

        }
    }

    private String getRequestParamOutput(RequestBody body, String serviceName) {
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset charset = StandardCharsets.UTF_8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(StandardCharsets.UTF_8);
            }
            assert charset != null;
            return truncateOutputStr(buffer.readString(charset));
        } catch (IOException e) {
            log.error("print params error, serviceName: {}", serviceName, e);
            return "";
        }
    }

    private String truncateOutputStr(String output) {
        if (!truncateResponseOutput ||
                CheckEmptyUtil.isEmpty(output) ||
                output.length() <= outputStringMaxLen) {
            return output;
        }

        return output.substring(0, outputStringMaxLen) + "...";
    }
}
