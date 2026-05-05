package com.example.walletzen.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.walletzen.model.Transaction;
import com.example.walletzen.repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends ViewModel {

    TransactionRepository repository
            = new TransactionRepository();

    public List<Transaction> getAllTransactions(
            Context context
    ){

        return repository.getAllTransactions(context);

    }

    public void insertTransaction(
            Context context,
            Transaction transaction
    ){

        repository.insertTransaction(
                context,
                transaction
        );

    }

}