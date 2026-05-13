package com.walletzen.controller;

import com.walletzen.model.Transaction;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TransactionController {

    List<Transaction> list = new ArrayList<>();

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {

        return list;
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(
            @RequestBody Transaction transaction
    ) {

        list.add(transaction);

        return transaction;
    }
}