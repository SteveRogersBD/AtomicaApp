package com.example.atomica.api;

import com.example.atomica.responses.YTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface YouTubeApi {

    @GET("search")
    @Headers(
            {
                    "x-rapidapi-key: 353b66e372mshc1bc6556a7cb404p1b2297jsn6230a7927dd4",
                    "x-rapidapi-host: youtube138.p.rapidapi.com"

            }
    )
    Call<YTResponse> searchVideos(
            @Query("q") String query
    );
}
