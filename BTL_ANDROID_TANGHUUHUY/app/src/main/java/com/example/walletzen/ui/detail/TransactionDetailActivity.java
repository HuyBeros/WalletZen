package com.example.walletzen.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;

public class TransactionDetailActivity
        extends AppCompatActivity {

    ImageView btnBack;

    TextView txtIcon;
    TextView txtAmount;
    TextView txtCategory;
    TextView txtDate;
    TextView txtTime;
    TextView txtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_transaction_detail
        );

        btnBack =
                findViewById(R.id.btnBack);

        txtIcon =
                findViewById(R.id.txtIcon);

        txtAmount =
                findViewById(R.id.txtAmount);

        txtCategory =
                findViewById(R.id.txtCategory);

        txtDate =
                findViewById(R.id.txtDate);

        txtTime =
                findViewById(R.id.txtTime);

        txtNote =
                findViewById(R.id.txtNote);

        String title =
                getIntent().getStringExtra("title");

        String amount =
                getIntent().getStringExtra("amount");

        String icon =
                getIntent().getStringExtra("icon");

        String date =
                getIntent().getStringExtra("date");

        String time =
                getIntent().getStringExtra("time");

        String note =
                getIntent().getStringExtra("note");

        txtIcon.setText(icon);

        txtAmount.setText("-" + amount);

        txtCategory.setText(title);

        txtDate.setText(date);

        txtTime.setText(time);

        txtNote.setText(note);

        btnBack.setOnClickListener(v -> {

            finish();

        });

    }

}