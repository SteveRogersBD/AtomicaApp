package com.example.atomica.utils;

import com.example.atomica.api.YouTubeApi;
import com.example.atomica.responses.YTResponse;
import com.example.atomica.retroclients.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YTUtil {
    public void fetchVideos(String query, VideoCallBack callBack)
    {
        YouTubeApi apiService = RetrofitClient.videoFit();
        Call<YTResponse> call = apiService.searchVideos(query);
        call.enqueue(new Callback<YTResponse>() {
            @Override
            public void onResponse(Call<YTResponse> call, Response<YTResponse> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    try{
                        callBack.onSuccess(response.body());
                    }catch (Exception e)
                    {
                        callBack.onFailure(e.getMessage());
                    }
                }
                else{
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<YTResponse> call, Throwable throwable) {
                callBack.onFailure(throwable.getMessage());
            }
        });
    }

    public interface VideoCallBack{
        void onSuccess(YTResponse response);
        void onFailure(String message);
    }
}
