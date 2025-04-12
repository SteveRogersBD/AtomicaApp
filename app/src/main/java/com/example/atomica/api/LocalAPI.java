package com.example.atomica.api;

import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalAPI {

    @POST("public/user/register")
    Call<ApiResponse>registerUser(@Body User user);

    @POST("public/user/log-in")
    Call<ApiResponse>loginUser(@Query("email") String email,
                               @Query("password") String password);

    @GET("public/user/verify-JWT")
    Call<ApiResponse>getUser(@Header("Authorization") String token);
}
