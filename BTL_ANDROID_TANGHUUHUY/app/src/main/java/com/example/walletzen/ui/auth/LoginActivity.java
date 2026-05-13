package com.example.walletzen.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;
import com.example.walletzen.model.User;
import com.example.walletzen.network.ApiService;
import com.example.walletzen.network.RetrofitClient;
import com.example.walletzen.ui.home.HomeActivity;
import com.google.android.material.checkbox.MaterialCheckBox;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegister;
    TextView txtForgotPassword;

    EditText edtEmail;
    EditText edtPassword;

    Button btnLogin;

    MaterialCheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtRegister = findViewById(R.id.txtRegister);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        cbRemember = findViewById(R.id.cbRemember);

        // LOAD SAVED ACCOUNT
        SharedPreferences preferences =
                getSharedPreferences("LOGIN_PREF", MODE_PRIVATE);

        String savedEmail = preferences.getString("email", "");
        String savedPassword = preferences.getString("password", "");

        edtEmail.setText(savedEmail);
        edtPassword.setText(savedPassword);

        txtRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );

        txtForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class))
        );

        btnLogin.setOnClickListener(v -> {

            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            User user = new User(email, password);

            ApiService apiService =
                    RetrofitClient.getRetrofit().create(ApiService.class);

            apiService.login(user).enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (response.isSuccessful()) {

                        User userResponse = response.body();

                        if (userResponse != null) {

                            // SAVE LOGIN IF CHECKED
                            if (cbRemember.isChecked()) {

                                SharedPreferences.Editor editor =
                                        getSharedPreferences("LOGIN_PREF", MODE_PRIVATE).edit();

                                editor.putString("email", email);
                                editor.putString("password", password);
                                editor.apply();
                            }

                            Toast.makeText(LoginActivity.this,
                                    "Login Success",
                                    Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();

                        } else {

                            Toast.makeText(LoginActivity.this,
                                    "Sai tài khoản hoặc mật khẩu",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Toast.makeText(LoginActivity.this,
                            t.getMessage(),
                            Toast.LENGTH_LONG).show();

                    t.printStackTrace();
                }
            });
        });
    }
}