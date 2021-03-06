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

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerViewKelas;
    RecyclerView recyclerViewSeminar;

    DBHelper MyDB;
    ArrayList<String> id_kelas, nama_kelas, kategori_kelas, id_seminar, nama_seminar, kategori_seminar;
    CustomAdapterKelas customAdapterKelas;
    CustomAdapterSeminar customAdapterSeminar;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set Username in Dashboard
        TextView getUsername = findViewById(R.id.txtHai);
        Bundle bundle = getIntent().getExtras();
        getUsername.setText("Haiii, "+bundle.getString("Username"));
        username = bundle.getString("Username");

        //Initialize Add Assign Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //Perform Item SelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle setUsername = new Bundle();
                setUsername.putString("Username", username);
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        return true;
                    case R.id.kelas:
                        Intent intentKelas = new Intent(getApplicationContext(), KelasActivity.class);
                        intentKelas.putExtras(setUsername);
                        startActivity(intentKelas);
                        overridePendingTransition(0,0);
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
        recyclerViewKelas = findViewById(R.id.rvKelasPopuler);
        recyclerViewSeminar = findViewById(R.id.rvSeminarPopuler);

        MyDB = new DBHelper(HomeActivity.this);
        id_kelas = new ArrayList<>();
        nama_kelas = new ArrayList<>();
        kategori_kelas = new ArrayList<>();
        id_seminar = new ArrayList<>();
        nama_seminar = new ArrayList<>();
        kategori_seminar = new ArrayList<>();

        storeDataKelasInArray();
        storeDataSeminarInArray();

        customAdapterKelas = new CustomAdapterKelas(HomeActivity.this, id_kelas,nama_kelas,kategori_kelas);
        recyclerViewKelas.setAdapter(customAdapterKelas);
        recyclerViewKelas.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        customAdapterSeminar = new CustomAdapterSeminar(HomeActivity.this, id_seminar,nama_seminar,kategori_seminar);
        recyclerViewSeminar.setAdapter(customAdapterSeminar);
        recyclerViewSeminar.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    public void getUser(String user) {
        username = user;
    }

    void storeDataKelasInArray() {
        Cursor cursor = MyDB.readDataKelasPopuler();
        if (cursor.getCount() == 0) {
            Toast.makeText(HomeActivity.this, "No Data Kelas", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                id_kelas.add(cursor.getString(0));
                nama_kelas.add(cursor.getString(1));
                kategori_kelas.add(cursor.getString(2));
            }
        }
    }

    void storeDataSeminarInArray() {
        Cursor cursor = MyDB.readDataSeminarPopuler();
        if (cursor.getCount() == 0) {
            Toast.makeText(HomeActivity.this, "No Data Seminar", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                id_seminar.add(cursor.getString(0));
                nama_seminar.add(cursor.getString(1));
                kategori_seminar.add(cursor.getString(2));
            }
        }
    }
}