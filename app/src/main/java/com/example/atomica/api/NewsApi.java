package com.example.atomica.api;


import com.example.atomica.responses.ImageResponse;
import com.example.atomica.responses.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("search.json")
    Call<NewsResponse>getNews(@Query("api_key") String apiKey,
                              @Query("engine") String engine,
                              @Query("q") String query,
                              @Query("gl") String gl,
                              @Query("hl") String hl);

    @GET("search.json")
    Call<ImageResponse>getImages(@Query("api_key") String apiKey,
                                 @Query("engine") String engine,
                                 @Query("q") String query);

}
