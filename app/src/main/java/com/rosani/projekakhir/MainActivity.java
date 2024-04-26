package com.rosani.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rosani.projekakhir.databinding.ActivityDataMahasiswaBinding;
import com.rosani.projekakhir.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Dashboard");

        Button lihat_data = findViewById(R.id.lihat_data);
        lihat_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, DataMahasiswa.class);
                startActivity(intent);
            }
        });

        Button input_data = findViewById(R.id.input_data);
        input_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity. this, InputData.class);
                startActivity(intent);
            }
        });

        Button informasi = findViewById(R.id.informasi);
        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity. this, Informasi.class);
                startActivity(intent);
            }
        });

        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan kode untuk logout di sini
                // Misalnya, bersihkan sesi pengguna

                // Buat Intent untuk kembali ke halaman login
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                // Hapus semua aktivitas yang ada di atas halaman login dari tumpukan aktivitas
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Mulai aktivitas login
                startActivity(intent);
            }
        });

    }

}