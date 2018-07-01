package com.sun.xiaotian.demo.test.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

public class TestGetArticle {

    private static final String baseUrl = "http://www.sunfeilong.com:8080";

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .build();

        GetArticle getArticle = retrofit.create(GetArticle.class);
        Call<ResponseBody> call = getArticle.getArticleInfo("lan2tian2_AboutMe.md_7a6f8dd3a6c4f8fa89f7f1e7ef38ca59", "text");


        Response<ResponseBody> response = call.execute();
        if(response.isSuccessful()) {
            System.out.println(response.toString());
        }
    }
}
