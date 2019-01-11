package com.xiaotian.demo.test.okhttp;

import okhttp3.*;

import java.io.IOException;

public class OkHttpTest {
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("status", "1")
                .add("age", "23")
                .build();

        Request request = new Request.Builder()
                .url("http://www.sunfeilong.cn")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

}
