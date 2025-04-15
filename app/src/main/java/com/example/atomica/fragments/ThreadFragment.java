package com.example.atomica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.atomica.R;
import com.example.atomica.adapters.PostAdapter;
import com.example.atomica.api.LocalAPI;
import com.example.atomica.databinding.FragmentThreadBinding;
import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.Post;
import com.example.atomica.retroclients.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreadFragment extends Fragment {

    public ThreadFragment() {
        // Required empty public constructor
    }
    FragmentThreadBinding binding;
    LocalAPI localAPI;
    PostAdapter adapter;
    List<Post>posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThreadBinding.inflate(inflater,container,false);
        posts = new ArrayList<>();
        adapter = new PostAdapter(getContext(),posts);
        binding.postRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false));
        binding.postRecycler.setAdapter(adapter);
        fetchPosts();
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            fetchPosts();
            binding.swipeRefreshLayout.setRefreshing(false);
        });

        return binding.getRoot();
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    public void fetchPosts(){
        localAPI = RetrofitClient.localApi();
        Call<ApiResponse<List<Post>>> call = localAPI.getAllPosts();
        call.enqueue(new Callback<ApiResponse<List<Post>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Post>>> call, Response<ApiResponse<List<Post>>> response) {
                try{
                    if (response.isSuccessful() && response.body() != null)
                    {
                        posts.clear();
                        posts.addAll(response.body().data);
                        adapter.notifyDataSetChanged();

                        Log.e("Success",response.message());

                    }else{
                        Log.e("Else",response.message());
                    }
                }catch (Exception e)
                {
                    Log.e("Catch",e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<Post>>> call, Throwable throwable) {

            }
        });
    }

}