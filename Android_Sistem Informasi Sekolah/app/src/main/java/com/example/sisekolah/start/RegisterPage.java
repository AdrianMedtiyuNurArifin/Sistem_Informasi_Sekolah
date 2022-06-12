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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sisekolah.HomeActivity;
import com.example.sisekolah.R;
import com.example.sisekolah.helper.SharedPrefManager;
import com.example.sisekolah.response.RegisterResponse;
import com.example.sisekolah.helper.ApiConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtName;
    RadioGroup radioGroupAccess;
    String userBaru;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //jika user telah login
        if (SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this, HomeActivity.class)); //ganti class menjadi class home
            return;
        }

        edtUsername = (EditText) findViewById(R.id.usernameReg);
        edtPassword = (EditText) findViewById(R.id.passwordReg);
        edtName = (EditText) findViewById(R.id.namaReg);
        radioGroupAccess = (RadioGroup) findViewById(R.id.radioAksesReg);
        registerButton = (Button) findViewById(R.id.signBut);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser(){
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        final String nama = edtName.getText().toString().trim();
        final String akses = ((RadioButton) findViewById(radioGroupAccess.getCheckedRadioButtonId())).getText().toString();

        //mengecek apakah semua data terisi
        if (TextUtils.isEmpty(username)){
            edtUsername.setError("Masukkan username");
            edtUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            edtPassword.setError("Masukkan password");
            edtPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nama)) {
            edtName.setError("Masukkan nama anda");
            edtName.requestFocus();
            return;
        }

        //jika semua data telah terisi
        Log.d("Register", "Sebelum Register");
        register();
        Log.d("Register", "Setelah Register");

    }

    private void register(){

        ApiConfig.getApiService().registerUser(
                edtUsername.getText().toString().trim(),
                edtPassword.getText().toString().trim(),
                edtName.getText().toString().trim(),
                ((RadioButton) findViewById(radioGroupAccess.getCheckedRadioButtonId())).getText().toString()
        ).enqueue(new Callback<RegisterResponse>(){

            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                assert response.body() != null;
                userBaru = response.body().getMessage();
                if (userBaru != null){
                    Toast.makeText(getApplicationContext(), userBaru, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), userBaru, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), userBaru, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoginAction(View view) {
        Intent loginNow = new Intent(getApplicationContext(), LoginPage.class);
        startActivity(loginNow);
        finish();
    }
}