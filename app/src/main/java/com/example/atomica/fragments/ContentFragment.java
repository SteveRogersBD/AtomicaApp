package com.example.atomica.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.atomica.adapters.NewsAdapter;
import com.example.atomica.adapters.YTAdapter;
import com.example.atomica.databinding.FragmentContentBinding;
import com.example.atomica.responses.NewsResponse;
import com.example.atomica.responses.YTResponse;
import com.example.atomica.utils.NewsApiUtil;
import com.example.atomica.utils.YTUtil;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;
    private YTAdapter adapter;
    ArrayList<YTResponse.Content> videos;
    private NewsAdapter newsAdapter;
    private List<NewsResponse.NewsResult> newsResults = new ArrayList<>();
    private NewsApiUtil newsApiUtil;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);



        // Initialize adapter with an empty list and attach it immediately
        videos = new ArrayList<>();
        adapter = new YTAdapter(getContext(), videos);
        binding.videoRecycler.setAdapter(adapter);


        // Fetch YouTube videos asynchronously
        fetchVideos();

        //fetch news articles
        newsApiUtil = new NewsApiUtil(requireContext());
        newsAdapter = new NewsAdapter(requireContext(), newsResults);
        binding.newsRecycler.setAdapter(newsAdapter);
        binding.newsRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        updateNewsArticles("Chemical Reactions");

        return binding.getRoot();
    }

    private void fetchVideos(){
        YTUtil util = new YTUtil();
        util.fetchVideos("Hydrogen", new YTUtil.VideoCallBack() {
            @Override
            public void onSuccess(YTResponse response) {
                videos.clear();
                videos.addAll(response.contents);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateNewsArticles(String query) {

        newsApiUtil.getNews(query, new NewsApiUtil.NewsCallBack() {
            @Override
            public void onSuccess(List<NewsResponse.NewsResult> newsList) {

                newsResults.clear();
                newsResults.addAll(newsList);
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {

                Toast.makeText(requireContext(), "Failed to load news: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}