package com.rosani.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMahasiswa extends AppCompatActivity {

    private EditText editTextNama, editTextNIM, editTextJenisKelamin, editTextPerguruanTinggi, editTextProdi;
    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        // Inisialisasi elemen UI
        editTextNama = findViewById(R.id.editTextNama);
        editTextNIM = findViewById(R.id.editTextNIM);
        editTextJenisKelamin = findViewById(R.id.editTextJenisKelamin);
        editTextPerguruanTinggi = findViewById(R.id.editTextPerguruanTinggi);
        editTextProdi = findViewById(R.id.editTextProdi);

        // Mendapatkan data mahasiswa yang dikirim dari aktivitas sebelumnya
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mahasiswa = (Mahasiswa) extras.getSerializable("mahasiswa");
            if (mahasiswa != null) {
                // Mengisi nilai awal dari data mahasiswa ke dalam elemen UI
                editTextNama.setText(mahasiswa.getNama());
                editTextNIM.setText(String.valueOf(mahasiswa.getNim()));
                editTextJenisKelamin.setText(mahasiswa.getJenisKelamin());
                editTextPerguruanTinggi.setText(mahasiswa.getPerguruanTinggi());
                editTextProdi.setText(mahasiswa.getProdi());
            }
        }
    }

    // Metode untuk menyimpan perubahan data mahasiswa yang diedit
    private void saveChanges() {
        // Mendapatkan nilai yang dimasukkan oleh pengguna
        String nama = editTextNama.getText().toString().trim();
        int nim = Integer.parseInt(editTextNIM.getText().toString().trim());
        String jenisKelamin = editTextJenisKelamin.getText().toString().trim();
        String perguruanTinggi = editTextPerguruanTinggi.getText().toString().trim();
        String prodi = editTextProdi.getText().toString().trim();

        // Update data mahasiswa
        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);
        mahasiswa.setJenisKelamin(jenisKelamin);
        mahasiswa.setPerguruanTinggi(perguruanTinggi);
        mahasiswa.setProdi(prodi);

        // TODO: Implementasi untuk menyimpan perubahan ke database atau penyimpanan data lainnya

        // Tampilkan pesan sukses
        Toast.makeText(this, "Data mahasiswa berhasil diperbarui", Toast.LENGTH_SHORT).show();

        // Kembali ke aktivitas sebelumnya
        finish();
    }

}