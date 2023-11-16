package com.example.news50.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news50.view.FragmentBusiness;
import com.example.news50.view.FragmentEnterTainment;
import com.example.news50.view.FragmentHealth;
import com.example.news50.view.FragmentHome;
import com.example.news50.view.FragmentScience;
import com.example.news50.view.FragmentSport;
import com.example.news50.view.FragmentTech;


public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentTech();
            case 2:
                return new FragmentBusiness();
            case 3:
                return new FragmentScience();
            case 4:
                return new FragmentSport();
            case 5:
                return new FragmentEnterTainment();
            case 6:
                return new FragmentHealth();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 7;
    }

}
