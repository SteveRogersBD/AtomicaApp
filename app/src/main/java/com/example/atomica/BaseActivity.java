package com.example.atomica;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        GradientDrawable gradient = (GradientDrawable) getResources().
                getDrawable(R.drawable.base_bg);
        Window window = getWindow();
        int topColor = gradient.getColors()[0];
        int bottomColor = gradient.getColors()[1];
        window.setStatusBarColor(topColor);
        window.setNavigationBarColor(Color.WHITE);

    }
}