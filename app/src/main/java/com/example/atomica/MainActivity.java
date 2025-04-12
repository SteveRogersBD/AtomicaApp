package com.example.atomica;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrieveJWT();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragmentContainer);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        GradientDrawable gradient = (GradientDrawable) getResources().
                getDrawable(R.drawable.base_bg);
        Window window = getWindow();

        // Get the top color of the gradient
        int topColor = Color.parseColor("#25395B");
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // Set the status bar color to the top color of the gradient
        window.setStatusBarColor(topColor);

        // Set the navigation bar color to the top color of the gradient as well (optional)
        window.setNavigationBarColor(topColor);
    }

    private void retrieveJWT() {
        sp = getSharedPreferences("JWT_TOKEN",MODE_PRIVATE);
        String token = sp.getString("token",null);
        //if(token!=null) //Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }
}