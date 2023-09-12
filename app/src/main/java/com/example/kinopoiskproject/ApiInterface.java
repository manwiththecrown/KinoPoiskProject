package com.example.kinopoiskproject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top")
    Call<Root> getMoviesTop(@Query("type") String type, @Query("page") int page);

    @GET("{id}/videos")
    Call<Trailers> getTrailer(@Path("id") int id);

    @GET("top")
    Call<Root> getTopFuture(@Query("type") String type, @Query("page") int page);
}