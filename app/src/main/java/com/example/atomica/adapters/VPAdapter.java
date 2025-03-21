package com.example.atomica.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.atomica.fragments.ContentFragment;
import com.example.atomica.fragments.HomeFragment;
import com.example.atomica.fragments.ThreadFragment;

public class VPAdapter extends FragmentPagerAdapter {

    public VPAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new ContentFragment();
            case 1:
                return new ThreadFragment();
            default:
                return new ContentFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Content";
            case 1:
                return "Thread";
            default:
                return "Thread";
        }
    }
}
