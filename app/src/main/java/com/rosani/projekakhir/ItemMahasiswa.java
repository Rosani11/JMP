package com.rosani.projekakhir;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.List;

public class ItemMahasiswa extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_mahasiswa);

        listView = findViewById(R.id.list_view);

        // Mendapatkan semua data mahasiswa dari database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Mahasiswa> mahasiswaList = databaseHelper.getAllMahasiswa();

        // Menggunakan adapter untuk menampilkan data mahasiswa di ListView
        ArrayAdapter<Mahasiswa> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mahasiswaList);
        listView.setAdapter(adapter);
    }
}
