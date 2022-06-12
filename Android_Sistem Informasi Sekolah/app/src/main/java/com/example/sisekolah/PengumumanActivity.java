package com.example.sisekolah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sisekolah.helper.ApiConfig;
import com.example.sisekolah.helper.SharedPrefManager;
import com.example.sisekolah.helper.URLs;
import com.example.sisekolah.model.Announcement;
import com.example.sisekolah.model.Pengumuman;
import com.example.sisekolah.response.PengumumanResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Pengumuman> data;
    private PengumumanAdapter adapter;

    private DataAdapter dataAdapter;
    private List<Announcement> dataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);
        initViews();

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.pengumuman);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.pengumuman:
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), PetaActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    private void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Log.d("Error", "Load JSON");

        ApiConfig.getApiService().getPengumuman().enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Announcement>> call, @NonNull Response<ArrayList<Announcement>> response) {
                dataArrayList = response.body();
                dataAdapter = new DataAdapter(getApplicationContext(), dataArrayList);
                recyclerView.setAdapter(dataAdapter);
                Log.d("Error", "JSON successful");
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Announcement>> call, @NonNull Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
//        ApiConfig.getApiService().getPengumuman().enqueue(new Callback<PengumumanResponse>() {
//            @Override
//            public void onResponse(Call<PengumumanResponse> call, Response<PengumumanResponse> response) {
//                PengumumanResponse pengumumanResponse = response.body();
//                data = new ArrayList<>(Arrays.asList(pengumumanResponse.getPengumumen()));
//                adapter = new PengumumanAdapter(data);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<PengumumanResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Terjadi kesalahan dalam mendapatkan pengumuman", Toast.LENGTH_SHORT).show();
//                Log.d("Error", t.getMessage());
//            }
//        });
    }
}