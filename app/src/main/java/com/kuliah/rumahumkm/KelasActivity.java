package com.kuliah.rumahumkm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class KelasActivity extends AppCompatActivity {

    RecyclerView recyclerViewKelas;

    DBHelper MyDB;
    ArrayList<String> id_kelas, nama_kelas, kategori_kelas;
    CustomAdapterKelas customAdapterKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        //Initialize Add Assign Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.kelas);

        //Perform Item SelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.kelas:
                        return true;
                    case R.id.seminar:
                        startActivity(new Intent(getApplicationContext()
                                ,SeminarActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.akun:
                        startActivity(new Intent(getApplicationContext()
                                ,AkunActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Recycle View
        recyclerViewKelas = findViewById(R.id.rvKelasPopuler);

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