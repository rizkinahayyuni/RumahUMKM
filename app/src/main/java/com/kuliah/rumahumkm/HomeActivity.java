package com.kuliah.rumahumkm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerViewKelas;
    RecyclerView recyclerViewSeminar;

    DBHelper MyDB;
    ArrayList<String> id_kelas, nama_kelas, kategori_kelas, id_seminar, nama_seminar, kategori_seminar;
    CustomAdapterKelas customAdapterKelas;
    CustomAdapterSeminar customAdapterSeminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

    void storeDataKelasInArray() {
        Cursor cursor = MyDB.readAllDataKelas();
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
        Cursor cursor = MyDB.readAllDataSeminar();
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