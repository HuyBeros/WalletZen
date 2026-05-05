package com.example.walletzen.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;
import com.example.walletzen.ui.home.HomeActivity;
import com.example.walletzen.ui.statistics.StatisticsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.nav_profile);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                startActivity(
                        new Intent(
                                ProfileActivity.this,
                                HomeActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;

            } else if(item.getItemId() == R.id.nav_statistics){

                startActivity(
                        new Intent(
                                ProfileActivity.this,
                                StatisticsActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;

            } else if(item.getItemId() == R.id.nav_profile){

                return true;
            }

            return false;

        });

    }
}