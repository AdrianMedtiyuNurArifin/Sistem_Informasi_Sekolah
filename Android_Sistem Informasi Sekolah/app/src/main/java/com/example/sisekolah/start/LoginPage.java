package com.example.sisekolah.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sisekolah.HomeActivity;
import com.example.sisekolah.R;
import com.example.sisekolah.helper.SharedPrefManager;
import com.example.sisekolah.response.LoginResponse;
import com.example.sisekolah.helper.ApiConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    String userLogin, userUsername, userAccess;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        edtUsername = (EditText) findViewById(R.id.usernameLogin);
        edtPassword = (EditText) findViewById(R.id.passwordLogin);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        if (SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
    }


    private void LoginUser(){
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        //mengecek data input
        if (TextUtils.isEmpty(username)){
            edtUsername.setError("Masukkan username anda");
            edtUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            edtPassword.setError("Masukkan Password anda");
            edtPassword.requestFocus();
            return;
        }

        //jika semua data telah terisi
        Log.d("Login", "Data terisi semua");
        login();
        Log.d("Login", "User berhasil login");
    }

    private void login(){
        ApiConfig.getApiService().userLogin(
                edtUsername.getText().toString().trim(),
                edtPassword.getText().toString().trim()
        ).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                assert response.body() != null;
                userLogin = response.body().getMessage();

                userUsername = response.body().getUsername();
                userAccess = response.body().getAkses();

                if (userLogin != null){
                    Toast.makeText(getApplicationContext(), userLogin, Toast.LENGTH_SHORT).show();

                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(userUsername, userAccess);

                    Intent loginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(loginIntent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Username atau Password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RegisterAction(View view) {
        Intent register = new Intent(getApplicationContext(), RegisterPage.class);
        startActivity(register);
    }
}