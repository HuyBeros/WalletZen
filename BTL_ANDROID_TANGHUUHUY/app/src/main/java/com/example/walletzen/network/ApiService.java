package com.example.walletzen.network;

import com.example.walletzen.model.Transaction;
import com.example.walletzen.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("transactions")
    Call<List<Transaction>> getTransactions();

    @POST("api/login")
    Call<User> login(
            @Body User user
    );

}