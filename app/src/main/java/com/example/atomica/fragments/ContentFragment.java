package com.example.atomica.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.atomica.adapters.YTAdapter;
import com.example.atomica.api.YouTubeApi;
import com.example.atomica.databinding.FragmentContentBinding;
import com.example.atomica.responses.YTResponse;
import com.example.atomica.retroclients.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;
    private YTAdapter adapter;
    ArrayList<YTResponse.Content> videos;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);



        // Initialize adapter with an empty list and attach it immediately
        videos = new ArrayList<>();
        adapter = new YTAdapter(getContext(), videos);

        // Set up RecyclerView with a horizontal LinearLayoutManager
        binding.videoRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.videoRecycler.setAdapter(adapter);

        // Fetch YouTube videos asynchronously
        fetchYouTubeVideos();

        return binding.getRoot();
    }

    private void fetchYouTubeVideos() {
        YouTubeApi apiService = RetrofitClient.videoFit();
        Call<YTResponse> call = apiService.searchVideos("Hydrogen");

        call.enqueue(new Callback<YTResponse>() {
            @Override
            public void onResponse(Call<YTResponse> call, Response<YTResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<YTResponse.Content> videoList = response.body().contents;
                    if (videoList != null && !videoList.isEmpty()) {
                        // Update the adapter with new data
                        videos.clear();
                        videos.addAll(videoList);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e("API_ERROR", "No videos found in response.");
                    }
                } else {
                    Log.e("API_ERROR", "Response failed: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<YTResponse> call, Throwable t) {
                Log.e("API_ERROR", "Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}