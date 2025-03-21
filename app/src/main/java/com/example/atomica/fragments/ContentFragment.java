package com.example.atomica.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.atomica.R;
import com.example.atomica.adapters.YTAdapter;
import com.example.atomica.api.YouTubeApi;
import com.example.atomica.databinding.FragmentContentBinding;
import com.example.atomica.responses.YTResponse;
import com.example.atomica.retroclients.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;
    private YTAdapter adapter;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);



        // Fetch and display YouTube videos
        fetchYouTubeVideos();

        return binding.getRoot();
    }

    private void fetchYouTubeVideos() {
        YouTubeApi apiService = RetrofitClient.getClient().create(YouTubeApi.class);

        Call<YTResponse> call = apiService.searchVideos("star wars", getString(R.string.api_key_yt));
        call.enqueue(new Callback<YTResponse>() {
            @Override
            public void onResponse(Call<YTResponse> call, Response<YTResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API_RESPONSE", "Success: " + response.body());
                    if (response.body() != null) {
                        List<YTResponse.VideoResult> videos = response.body().video_results;

                        if (videos != null && !videos.isEmpty()) {
                            adapter = new YTAdapter(getContext(), videos);
                            binding.videoRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                                    LinearLayoutManager.HORIZONTAL, false));
                            binding.videoRecycler.setAdapter(adapter);
                        } else {
                            Log.e("API_ERROR", "No videos found.");
                        }
                    } else {
                        Log.e("API_ERROR", "Response body is null.");
                    }
                } else {
                    Log.e("API_ERROR", "Response failed: " + response.code() + " " + response.message());
                }
            }


            @Override
            public void onFailure(Call<YTResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
