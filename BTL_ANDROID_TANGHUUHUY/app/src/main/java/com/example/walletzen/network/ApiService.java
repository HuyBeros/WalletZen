    package com.example.walletzen.network;

    import com.example.walletzen.model.Transaction;

    import java.util.List;

    import retrofit2.Call;
    import retrofit2.http.GET;

    public interface ApiService {

        @GET("transactions")
        Call<List<Transaction>> getTransactions();

    }