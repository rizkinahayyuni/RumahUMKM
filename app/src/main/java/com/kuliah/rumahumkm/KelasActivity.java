package com.kuliah.rumahumkm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class KelasActivity extends AppCompatActivity {

    RecyclerView recyclerViewKelas;

    DBHelper MyDB;
    ArrayList<String> id_kelas, nama_kelas, kategori_kelas;
    CustomAdapterKelas customAdapterKelas;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        // Set Username in Dashboard
        TextView getUsername = findViewById(R.id.txtHai);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");

        //Initialize Add Assign Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.kelas);

        //Perform Item SelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle setUsername = new Bundle();
                setUsername.putString("Username", username);
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
                        intentHome.putExtras(setUsername);
                        startActivity(intentHome);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.kelas:
                        return true;
                    case R.id.seminar:
                        Intent intentSeminar = new Intent(getApplicationContext(), SeminarActivity.class);
                        intentSeminar.putExtras(setUsername);
                        startActivity(intentSeminar);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.akun:
                        Intent intentAkun = new Intent(getApplicationContext(), AkunActivity.class);
                        intentAkun.putExtras(setUsername);
                        startActivity(intentAkun);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Recycle View
        recyclerViewKelas = findViewById(R.id.rvDaftarKelas);

        MyDB = new DBHelper(KelasActivity.this);
        id_kelas = new ArrayList<>();
        nama_kelas = new ArrayList<>();
        kategori_kelas = new ArrayList<>();

        storeDataKelasInArray();

        customAdapterKelas = new CustomAdapterKelas(KelasActivity.this, id_kelas,nama_kelas,kategori_kelas);
        recyclerViewKelas.setAdapter(customAdapterKelas);
        recyclerViewKelas.setLayoutManager(new LinearLayoutManager(KelasActivity.this));
    }

    void storeDataKelasInArray() {
        Cursor cursor = MyDB.readAllDataKelas();
        if (cursor.getCount() == 0) {
            Toast.makeText(KelasActivity.this, "No Data Kelas", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                id_kelas.add(cursor.getString(0));
                nama_kelas.add(cursor.getString(1));
                kategori_kelas.add(cursor.getString(2));
            }
        }
    }
}