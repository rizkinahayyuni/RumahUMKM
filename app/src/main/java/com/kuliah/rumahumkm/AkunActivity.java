package com.kuliah.rumahumkm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AkunActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        // Set Username in Dashboard
        TextView getUsername = findViewById(R.id.txtHai);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");

        //Initialize Add Assign Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.akun);

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
                        return true;
                }
                return false;
            }
        });
    }
}