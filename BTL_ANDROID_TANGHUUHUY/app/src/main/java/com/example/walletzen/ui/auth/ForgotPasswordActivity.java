package com.example.walletzen.ui.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;

public class ForgotPasswordActivity
        extends AppCompatActivity {

    ImageView btnBack;

    EditText edtEmail;

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_forgot_password
        );

        btnBack =
                findViewById(R.id.btnBack);

        edtEmail =
                findViewById(R.id.edtEmail);

        btnSend =
                findViewById(R.id.btnSend);

        // BACK

        btnBack.setOnClickListener(v -> {

            finish();

        });

        // SEND

        btnSend.setOnClickListener(v -> {

            String email =
                    edtEmail.getText().toString();

            if(email.isEmpty()){

                Toast.makeText(
                        this,
                        "Vui lòng nhập email",
                        Toast.LENGTH_SHORT
                ).show();

            }else{

                Toast.makeText(
                        this,
                        "Đã gửi yêu cầu lấy lại mật khẩu",
                        Toast.LENGTH_LONG
                ).show();

            }

        });

    }

}