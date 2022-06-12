package com.example.sisekolah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sisekolah.helper.URLs;
import com.example.sisekolah.model.Announcement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailPengumumanActivity extends AppCompatActivity {
    private Announcement announcement;
    String id = "id";
    String judul = "judul";
    String isi = "isi";
    TextView tvID, tvJudul, tvIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengumuman);

        tvID = (TextView) findViewById(R.id.tv_idPengumuman);
        tvJudul = (TextView) findViewById(R.id.tv_judulPengumuman);
        tvIsi = (TextView) findViewById(R.id.tv_isiPengumuman);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            announcement = new Announcement();
            announcement.setId(bundle.getString(URLs.CURRENT_ID));

            tvID.setText(bundle.getString(URLs.CURRENT_ID));
            tvJudul.setText(bundle.getString(URLs.CURRENT_TITLE));
            tvIsi.setText(bundle.getString(URLs.CURRENT_CONTENT));
        }

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
}