package com.example.walletzen.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        txtRegister = findViewById(R.id.txtRegister);

        txtRegister.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            LoginActivity.this,
                            RegisterActivity.class
                    )
            );

        });

    }
}