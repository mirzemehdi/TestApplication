package com.mmk.testapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mmk.testapplication.R;
import com.mmk.testapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= DataBindingUtil
                .setContentView(this,R.layout.activity_main);

        navController= Navigation.findNavController(this,R.id.nav_host_fragment_main);
        BottomNavigationView bottomNavigationView=binding.bottomNavigationMain;

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}