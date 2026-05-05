package com.example.walletzen.ui.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.walletzen.viewmodel.TransactionViewModel;
import com.example.walletzen.R;
import com.example.walletzen.ui.home.HomeActivity;
import com.example.walletzen.database.DatabaseClient;
import com.example.walletzen.model.Transaction;

public class AddTransactionActivity extends AppCompatActivity {

    EditText edtTitle, edtAmount;

    RadioButton radioIncome, radioExpense;

    Button btnSave;
    TransactionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_transaction);

        edtTitle = findViewById(R.id.edtTitle);

        edtAmount = findViewById(R.id.edtAmount);

        radioIncome = findViewById(R.id.radioIncome);

        radioExpense = findViewById(R.id.radioExpense);

        btnSave = findViewById(R.id.btnSave);

        viewModel = new ViewModelProvider(this)
                .get(TransactionViewModel.class);


        btnSave.setOnClickListener(v -> {

            String title = edtTitle.getText().toString();

            String amount = edtAmount.getText().toString();

            if(radioExpense.isChecked()){

                amount = "-" + amount + " đ";

            } else {

                amount = "+" + amount + " đ";

            }

            Transaction transaction = new Transaction(
                    title,
                    "Today",
                    amount
            );

            viewModel.insertTransaction(
                    this,
                    transaction
            );

            startActivity(
                    new Intent(
                            AddTransactionActivity.this,
                            HomeActivity.class
                    )
            );

            finish();

        });

    }
}