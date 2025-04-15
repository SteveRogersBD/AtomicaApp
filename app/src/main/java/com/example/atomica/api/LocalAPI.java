package com.example.atomica.api;

import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.Comment;
import com.example.atomica.responses.Post;
import com.example.atomica.responses.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalAPI {

    @POST("public/user/register")
    Call<ApiResponse>registerUser(@Body User user);

    @POST("public/user/log-in")
    Call<ApiResponse>loginUser(@Query("email") String email,
                               @Query("password") String password);

    @GET("public/user/verify-JWT")
    Call<ApiResponse<User>>getUser(@Header("Authorization") String token);

    @GET("post/public/get-all")
    Call<ApiResponse<List<Post>>>getAllPosts();

    @GET("public/user/{id}")
    Call<ApiResponse<User>>getUserById(@Path("id") long id);

    @GET("post/{id}")
    Call<ApiResponse<Post>>getPostById(@Path("id") long id);

    @POST
    Call<ApiResponse<Comment>>createComment(@Query ("postId") long postId,
                                            @Header("Authorization") String token);

}
