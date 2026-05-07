package com.example.walletzen.ui.auth;

import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegister;

    EditText edtEmail;
    EditText edtPassword;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        txtRegister = findViewById(R.id.txtRegister);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        txtRegister.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            LoginActivity.this,
                            RegisterActivity.class
                    )
            );

        });

        btnLogin.setOnClickListener(v -> {

            String email =
                    edtEmail.getText().toString();

            String password =
                    edtPassword.getText().toString();

            User user =
                    new User(email, password);

            ApiService apiService =
                    RetrofitClient
                            .getRetrofit()
                            .create(ApiService.class);

            apiService.login(user)
                    .enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(
                                Call<User> call,
                                Response<User> response
                        ) {

                            if(response.isSuccessful()){

                                User userResponse =
                                        response.body();

                                System.out.println(userResponse);

                                if(userResponse != null){

                                    Toast.makeText(
                                            LoginActivity.this,
                                            "Login Success",
                                            Toast.LENGTH_LONG
                                    ).show();

                                    startActivity(
                                            new Intent(
                                                    LoginActivity.this,
                                                    HomeActivity.class
                                            )
                                    );

                                    finish();

                                }else{

                                    Toast.makeText(
                                            LoginActivity.this,
                                            "Sai tài khoản hoặc mật khẩu",
                                            Toast.LENGTH_LONG
                                    ).show();

                                }

                            }

                        }

                        @Override
                        public void onFailure(
                                Call<User> call,
                                Throwable t
                        ) {

                            Toast.makeText(
                                    LoginActivity.this,
                                    t.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();

                        }

                    });

        });

    }

}