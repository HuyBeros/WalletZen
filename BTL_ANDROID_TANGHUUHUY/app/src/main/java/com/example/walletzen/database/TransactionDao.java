package com.example.walletzen.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.walletzen.model.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transactions")
    List<Transaction> getAllTransactions();

}