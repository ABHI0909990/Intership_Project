package com.example.intership_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.intership_project.R;
import com.example.intership_project.databinding.ActivityIntroBinding;

public class IntroActivity extends HomeActivity {
    private ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        binding = ActivityIntroBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.stBtn.setOnClickListener(v ->{
            Intent i = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(i);
        });

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}