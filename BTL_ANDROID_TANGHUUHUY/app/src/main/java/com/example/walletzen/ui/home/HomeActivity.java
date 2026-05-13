package com.example.walletzen.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walletzen.R;
import com.example.walletzen.adapter.TransactionAdapter;
import com.example.walletzen.model.Transaction;
import com.example.walletzen.network.ApiService;
import com.example.walletzen.network.RetrofitClient;
import com.example.walletzen.ui.profile.ProfileActivity;
import com.example.walletzen.ui.statistics.StatisticsActivity;
import com.example.walletzen.ui.transaction.AddTransactionActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvTransaction;

    TransactionAdapter adapter;

    List<Transaction> list;

    BottomNavigationView bottomNavigation;

    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        rvTransaction =
                findViewById(R.id.rvTransaction);

        bottomNavigation =
                findViewById(R.id.bottomNavigation);

        fabAdd =
                findViewById(R.id.fabAdd);

        list = new ArrayList<>();

        // ADAPTER

        adapter = new TransactionAdapter(
                this,
                list
        );

        rvTransaction.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvTransaction.setAdapter(adapter);

        // LOAD DATA

        loadTransactions();

        // BOTTOM NAVIGATION

        bottomNavigation.setSelectedItemId(
                R.id.nav_home
        );

        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                return true;

            }
            else if(item.getItemId()
                    == R.id.nav_statistics){

                startActivity(
                        new Intent(
                                HomeActivity.this,
                                StatisticsActivity.class
                        )
                );

                return true;

            }
            else if(item.getItemId()
                    == R.id.nav_profile){

                startActivity(
                        new Intent(
                                HomeActivity.this,
                                ProfileActivity.class
                        )
                );

                return true;
            }

            return false;

        });

        // FLOAT BUTTON

        fabAdd.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            AddTransactionActivity.class
                    )
            );

        });

        overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );

    }

    @Override
    protected void onResume() {

        super.onResume();

        loadTransactions();

    }

    private void loadTransactions(){

        ApiService apiService =
                RetrofitClient
                        .getRetrofit()
                        .create(ApiService.class);

        apiService.getTransactions()
                .enqueue(new Callback<List<Transaction>>() {

                    @Override
                    public void onResponse(
                            Call<List<Transaction>> call,
                            Response<List<Transaction>> response
                    ) {

                        if(response.isSuccessful()
                                && response.body() != null){

                            list.clear();

                            list.addAll(response.body());

                            adapter.notifyDataSetChanged();

                        }else{

                            Toast.makeText(
                                    HomeActivity.this,
                                    "No Data",
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                    }

                    @Override
                    public void onFailure(
                            Call<List<Transaction>> call,
                            Throwable t
                    ) {

                        Toast.makeText(
                                HomeActivity.this,
                                t.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                    }

                });

    }

}