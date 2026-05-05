package com.example.walletzen.repository;

import android.content.Context;

import com.example.walletzen.database.DatabaseClient;
import com.example.walletzen.model.Transaction;

import java.util.List;

public class TransactionRepository {

    public List<Transaction> getAllTransactions(Context context){

        return DatabaseClient
                .getInstance(context)
                .getAppDatabase()
                .transactionDao()
                .getAllTransactions();

    }

    public void insertTransaction(
            Context context,
            Transaction transaction
    ){

        DatabaseClient
                .getInstance(context)
                .getAppDatabase()
                .transactionDao()
                .insert(transaction);

    }

}