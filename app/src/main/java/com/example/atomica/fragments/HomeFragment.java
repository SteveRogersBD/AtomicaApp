package com.example.atomica.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atomica.R;
import com.example.atomica.adapters.VPAdapter;
import com.example.atomica.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        VPAdapter adapter = new VPAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.getTabAt(0).setIcon(R.drawable.blog_icon);
        binding.tabLayout.getTabAt(1).setIcon(R.drawable.globe_icon);

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;

    }



}