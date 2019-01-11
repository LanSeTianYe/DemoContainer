package com.xiaotian.demo.test.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetArticle {

    @GET("/fileInfo/getContentByFilePath")
    Call<ResponseBody> getArticleInfo(@Query("id") String id, @Query("type") String type);

    @POST("/fileInfo/getContentByFilePath")
    Call<ResponseBody> getArticleInfoPost(@Query("id") String id, @Query("type") String type);
}
