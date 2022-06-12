package com.example.sisekolah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sisekolah.baseMain.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PetaActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.info);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragment, new MapsFragment()).commit();

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
                        startActivity(new Intent(getApplicationContext(), PengumumanActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.info:
                        return true;
                }
                return false;
            }
        });
    }
}