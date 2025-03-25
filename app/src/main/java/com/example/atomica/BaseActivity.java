package com.example.atomica;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        GradientDrawable gradient = (GradientDrawable) getResources().
                getDrawable(R.drawable.base_bg);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.base_bg);
        int topColor = gradient.getColors()[2];
        int bottomColor = gradient.getColors()[1];
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(topColor);
        window.setNavigationBarColor(topColor);

    }
}