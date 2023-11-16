package com.example.news50.network;

import com.example.news50.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsWebServiceTech {
    @GET("/v2/top-headlines?country=us&category=technology&apiKey=87262d3450674bc89eba6a308e19343d")
    Call<News> getAllNews();
}
