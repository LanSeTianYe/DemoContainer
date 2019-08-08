package com.xiaotian.demo.test.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egls.cashloan.tools.CharsetTool;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * HTTP 请求工具类
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/7/29 20:30
 */
@Slf4j
public class HttpTool {

    /**
     * 请求超时时间，单位秒
     */
    private static final int REQUEST_TIMEOUT_SECONDS = 20;

    /**
     * OkHttp 拦截器，编码URL参数。
     */
    private static final Interceptor PARAM_ENCODE_INTERCEPTOR = chain -> {
        Request originalRequest = chain.request();
        String query = originalRequest.url().query();
        if (null == query) {
            return chain.proceed(originalRequest);
        }
        HttpUrl encodeUrl = originalRequest.url().newBuilder()
                .query(URLEncoder.encode(query, StandardCharsets.UTF_8.name()))
                .build();
        Request encodeParamRequest = originalRequest
                .newBuilder()
                .url(encodeUrl)
                .build();
        return chain.proceed(encodeParamRequest);
    };

    /**
     * 请求客户端
     */
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .addInterceptor(PARAM_ENCODE_INTERCEPTOR)
            .readTimeout(REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build();

    public static String get(String url, JSONObject params) {
        return get(HttpUrlTool.buildUrl(url, params));
    }

    public static String get(String url, Map<String, String> params) {
        return get(HttpUrlTool.buildUrl(url, params));
    }

    /**
     * get 请求
     *
     * @param urlWithParams url和参数
     * @return 响应结果
     */
    public static String get(String urlWithParams) {
        Request request = new Request.Builder()
                .url(urlWithParams)
                .get()
                .build();
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("HttpTool_get response is unsuccessful, response:{}", response.toString());
                throw new RequestNotSuccessException(String.format("HttpTool_get response is unsuccessful , response code : %s, response info: %s", response.code(), response.toString()));
            }
            ResponseBody responseBody = response.body();
            Objects.requireNonNull(responseBody);
            log.debug("HttpTool_get send request to {}, response is: {}", urlWithParams, response);
            return responseBody.string();
        } catch (Exception e) {
            String message = String.format("HttpTool_get send request to %s exception!", urlWithParams);
            log.error(message, e);
            throw new RequestNotSuccessException(message, e);
        }
    }

    public static String postUseTextData(String url, final String data) {
        return post(url, RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String postUseJsonData(String url, String data) {
        return post(url, RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String postUseFormData(String url, final JSONObject data) throws UnsupportedEncodingException {
        return postUseFormData(url, HttpUrlTool.buildParamString(data));
    }

    public static String postUseFormData(String url, final Map<?, ?> data) throws UnsupportedEncodingException {
        return postUseFormData(url, HttpUrlTool.buildParamString(data));
    }

    /**
     * 与get请求一样的,但是将参数放在body中,并且进行URL encode
     */
    public static String postUseFormData(String url, final String data) throws UnsupportedEncodingException {
        return post(url, RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"), URLEncoder.encode(data, CharsetTool.ENCODING_UTF_8)));
    }

    public static String postUseBinaryData(String url, final byte[] data) {
        return post(url, RequestBody.create(MediaType.parse("application/octet-stream"), data));
    }

    /**
     * post 请求
     *
     * @param url         url
     * @param requestBody 请求参数
     * @return 响应数据
     */
    public static String post(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("HttpTool_post response is unsuccessful, response:{}", response.toString());
                throw new RequestNotSuccessException(String.format("HttpTool_post response is unsuccessful , response code : %s, response info: %s", response.code(), response.toString()));
            }
            ResponseBody responseBody = response.body();
            Objects.requireNonNull(responseBody);
            log.debug("HttpTool_post send request to {}, request body: {}, response: {}", url, requestBody, JSON.toJSONString(response));
            return responseBody.string();
        } catch (Exception e) {
            String message = String.format("HttpTool_post send request to url: [%s] exception. request body: [%s]", url, requestBody);
            log.error(message, e);
            throw new RequestNotSuccessException(message, e);
        }
    }
}
