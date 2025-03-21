package com.example.atomica.api;

import com.example.atomica.responses.YTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApi {

    @GET("search.json?engine=youtube")
    Call<YTResponse> searchVideos(
            @Query("search_query") String query,
            @Query("api_key") String apiKey
    );
}
