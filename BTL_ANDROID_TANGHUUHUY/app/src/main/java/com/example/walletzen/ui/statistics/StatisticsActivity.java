package com.example.walletzen.ui.statistics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;
import com.example.walletzen.ui.home.HomeActivity;
import com.example.walletzen.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StatisticsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.nav_statistics);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                startActivity(
                        new Intent(
                                StatisticsActivity.this,
                                HomeActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;

            } else if(item.getItemId() == R.id.nav_statistics){

                return true;

            } else if(item.getItemId() == R.id.nav_profile){

                startActivity(
                        new Intent(
                                StatisticsActivity.this,
                                ProfileActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;
            }

            return false;

        });

    }
}