package com.example.kinopoiskproject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestBuilder {
    private static String URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/";
    private static Retrofit retrofit = null;

    static Retrofit buildRequest(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("X-API-KEY", "ed5a3d71-c545-46ed-b66e-7a207f508983")
                    .build();
            return chain.proceed(request);
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
