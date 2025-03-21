package com.example.atomica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atomica.R;
import com.example.atomica.databinding.FragmentThreadBinding;

public class ThreadFragment extends Fragment {

    public ThreadFragment() {
        // Required empty public constructor
    }
    FragmentThreadBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThreadBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}