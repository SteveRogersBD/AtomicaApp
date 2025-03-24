package com.example.atomica.retroclients;

import com.example.atomica.api.YouTubeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit VideoFit = new Retrofit.Builder()
            .baseUrl("https://youtube138.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static YouTubeApi videoFit(){return VideoFit.create(YouTubeApi.class);}
}

