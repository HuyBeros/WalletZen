package com.example.walletzen.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.walletzen.viewmodel.TransactionViewModel;
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

    TransactionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        rvTransaction = findViewById(R.id.rvTransaction);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        fabAdd = findViewById(R.id.fabAdd);

        list = new ArrayList<>();

        viewModel = new ViewModelProvider(this)
                .get(TransactionViewModel.class);

        list = viewModel.getAllTransactions(this);

        adapter = new TransactionAdapter(list);

        rvTransaction.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvTransaction.setAdapter(adapter);

        bottomNavigation.setSelectedItemId(R.id.nav_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                return true;

            } else if(item.getItemId() == R.id.nav_statistics){

                startActivity(
                        new Intent(
                                HomeActivity.this,
                                StatisticsActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;

            } else if(item.getItemId() == R.id.nav_profile){

                startActivity(
                        new Intent(
                                HomeActivity.this,
                                ProfileActivity.class
                        )
                );

                overridePendingTransition(0,0);

                return true;
            }

            return false;

        });

        fabAdd.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            AddTransactionActivity.class
                    )
            );

        });

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

                        System.out.println("API SUCCESS");

                        if(response.isSuccessful()){

                            Toast.makeText(
                                    HomeActivity.this,
                                    "API Success",
                                    Toast.LENGTH_LONG
                            ).show();

                        }else{

                            Toast.makeText(
                                    HomeActivity.this,
                                    "Response Error: " + response.code(),
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                    }

                    @Override
                    public void onFailure(
                            Call<List<Transaction>> call,
                            Throwable t
                    ) {

                        System.out.println(t.getMessage());

                        Toast.makeText(
                                HomeActivity.this,
                                t.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                    }

                });

    }

}