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

public class SeminarActivity extends AppCompatActivity {

    RecyclerView recyclerViewSeminar;

    DBHelper MyDB;
    ArrayList<String> id_seminar, nama_seminar, kategori_seminar;
    CustomAdapterSeminar customAdapterSeminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);

        //Initialize Add Assign Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.seminar);

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
                        startActivity(new Intent(getApplicationContext()
                                ,KelasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.seminar:
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
        recyclerViewSeminar = findViewById(R.id.rvSeminarPopuler);

        MyDB = new DBHelper(SeminarActivity.this);
        id_seminar = new ArrayList<>();
        nama_seminar = new ArrayList<>();
        kategori_seminar = new ArrayList<>();

        storeDataSeminarInArray();

        customAdapterSeminar = new CustomAdapterSeminar(SeminarActivity.this, id_seminar,nama_seminar,kategori_seminar);
        recyclerViewSeminar.setAdapter(customAdapterSeminar);
        recyclerViewSeminar.setLayoutManager(new LinearLayoutManager(SeminarActivity.this));
    }

    void storeDataSeminarInArray() {
        Cursor cursor = MyDB.readAllDataSeminar();
        if (cursor.getCount() == 0) {
            Toast.makeText(SeminarActivity.this, "No Data Seminar", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                id_seminar.add(cursor.getString(0));
                nama_seminar.add(cursor.getString(1));
                kategori_seminar.add(cursor.getString(2));
            }
        }
    }
}