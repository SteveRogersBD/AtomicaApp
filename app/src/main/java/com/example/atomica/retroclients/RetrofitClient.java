package com.example.atomica.retroclients;

import com.example.atomica.api.LocalAPI;
import com.example.atomica.api.NewsApi;
import com.example.atomica.api.YouTubeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit VideoFit = new Retrofit.Builder()
            .baseUrl("https://youtube138.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final Retrofit NewsFit = new Retrofit.Builder().
            baseUrl("https://serpapi.com/").
            addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final Retrofit LocalFit = new Retrofit.Builder().
            baseUrl("http://192.168.1.249:8084/").
            addConverterFactory(GsonConverterFactory.create())
            .build();
    public static YouTubeApi videoFit(){return VideoFit.create(YouTubeApi.class);}
    public static NewsApi newsApi(){return NewsFit.create(NewsApi.class);}
    public static LocalAPI localApi(){return LocalFit.create(LocalAPI.class);}
}

