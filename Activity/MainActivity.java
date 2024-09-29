package com.example.intership_project.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.example.intership_project.Adapter.sliderAdpter;
import com.example.intership_project.Model.SliderModel;
import com.example.intership_project.ViewModel.MainViewModel;
import com.example.intership_project.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel = new MainViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBanner();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(0);
    }

    private void initBanner(){
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getSlider().observe(this, banners -> {
            setupBanners(banners);
            binding.progressBar.setVisibility(View.GONE);
        });
        viewModel.loadSlider();
    }

    private void setupBanners(List<SliderModel> images){
        binding.viewPager2.setAdapter(new sliderAdpter(binding.viewPager2, images));
        binding.viewPager2.setClipToPadding(false);
        binding.viewPager2.setClipChildren(false);
        binding.viewPager2.setOffscreenPageLimit(3);
        binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer tranformer= new CompositePageTransformer();
        tranformer.addTransformer((new MarginPageTransformer(40)));
        binding.viewPager2.setPageTransformer(tranformer);

        if(images.size()>1){
            binding.dotIndicator.setVisibility(View.VISIBLE);
            binding.dotIndicator.attachTo(binding.viewPager2);
        }
    }
}