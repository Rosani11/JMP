package com.rosani.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.List;

public class DataMahasiswa extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mahasiswa);
        getSupportActionBar().setTitle("Data Mahasiswa");

        listView = findViewById(R.id.list_view);
        databaseHelper = new DatabaseHelper(this);

        // Mendapatkan semua data mahasiswa dari database
        List<Mahasiswa> mahasiswaList = databaseHelper.getAllMahasiswa();

        // Menggunakan adapter kustom MahasiswaAdapter
        MahasiswaAdapter adapter = new MahasiswaAdapter(this, mahasiswaList);
        listView.setAdapter(adapter);
    }
}
