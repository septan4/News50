package com.example.news50;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.news50.adapter.FragmentAdapter;
import com.example.news50.database.ApplicationDatabase;
import com.example.news50.model.Article;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements OnDataIsReady{
    ChipNavigationBar chipNavigationBar;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);
        chipNavigationBar = findViewById(R.id.navigationBar);
        executor = Executors.newSingleThreadExecutor();
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), this.getLifecycle());
        viewPager2.setUserInputEnabled(false);
        fragmentAdapter.notifyDataSetChanged();
        viewPager2.setAdapter(fragmentAdapter);
        chipNavigationBar.setItemSelected(R.id.home, true);
        viewPager2.getAdapter().notifyDataSetChanged();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        viewPager2.setCurrentItem(0, true);
                        break;
                    case R.id.tech:
                        viewPager2.setCurrentItem(1, true);
                        break;
                    case R.id.business:
                        viewPager2.setCurrentItem(2, true);
                        break;
                    case R.id.science:
                        viewPager2.setCurrentItem(3, true);
                        break;
                    case R.id.sport:
                        viewPager2.setCurrentItem(4, true);
                        break;
                    case R.id.entertaiment:
                        viewPager2.setCurrentItem(5, true);
                        break;
                    case R.id.health:
                        viewPager2.setCurrentItem(6, true);
                        break;

                }
            }

        });


    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager2.setCurrentItem(item, smoothScroll);
        chipNavigationBar.setItemSelected(R.id.home, false);
        if (viewPager2.getCurrentItem() == 1) {
            chipNavigationBar.setItemSelected(R.id.tech, true);
        } else if (viewPager2.getCurrentItem() == 2) {
            chipNavigationBar.setItemSelected(R.id.business, true);
        } else if (viewPager2.getCurrentItem() == 3) {
            chipNavigationBar.setItemSelected(R.id.science, true);
        } else if (viewPager2.getCurrentItem() == 4) {
            chipNavigationBar.setItemSelected(R.id.sport, true);
        } else if (viewPager2.getCurrentItem() == 5) {
            chipNavigationBar.setItemSelected(R.id.entertaiment, true);
        } else if (viewPager2.getCurrentItem() == 6) {
            chipNavigationBar.setItemSelected(R.id.health, true);
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() != 0) {
            viewPager2.setCurrentItem(0);
            chipNavigationBar.setItemSelected(R.id.home, true);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onDataRead(List<Article> articles) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}








