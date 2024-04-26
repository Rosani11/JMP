package com.rosani.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        // Mendeklarasikan dan menginisialisasi variabel EditText
        EditText editTextNIM = findViewById(R.id.nim_mahasiswa);
        EditText editTextNama = findViewById(R.id.nama_mahasiswa);
        EditText editTextJenisKelamin = findViewById(R.id.jenis_kelamin);
        EditText editTextPerguruanTinggi = findViewById(R.id.perguruanTinggi);
        EditText editTextProdi = findViewById(R.id.prodi);

        Button simpan = findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil data dari input fields
                int nim = Integer.parseInt(editTextNIM.getText().toString());
                String nama = editTextNama.getText().toString();
                String jenisKelamin = editTextJenisKelamin.getText().toString();
                String perguruanTinggi = editTextPerguruanTinggi.getText().toString();
                String prodi = editTextProdi.getText().toString();

                // Membuat objek Mahasiswa
                Mahasiswa mahasiswa = new Mahasiswa(nim, nama, jenisKelamin, perguruanTinggi, prodi);

                // Menyimpan data ke dalam database
                DatabaseHelper databaseHelper = new DatabaseHelper(InputData.this);
                databaseHelper.addMahasiswa(mahasiswa);

                // Kembali ke activity sebelumnya atau lakukan sesuai kebutuhan aplikasi Anda
                Intent intent = new Intent(InputData.this, DataMahasiswa.class);
                startActivity(intent);
            }
        });
    }
}