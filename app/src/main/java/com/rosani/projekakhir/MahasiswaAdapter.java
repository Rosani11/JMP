package com.rosani.projekakhir;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList) {
        super(context, 0, mahasiswaList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Mahasiswa mahasiswa = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_mahasiswa, parent, false);
        }

        // Temukan TextView di layout item_mahasiswa
        TextView namaTextView = convertView.findViewById(R.id.namaTextView);
        TextView nimTextView = convertView.findViewById(R.id.nimTextView);

        // Set informasi mahasiswa ke dalam TextView
        namaTextView.setText("Nama: " + mahasiswa.getNama());
        nimTextView.setText("NIM: " + mahasiswa.getNim());

        // Tambahkan event click ke item ListView
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan pop-up dengan pilihan ketika nama mahasiswa diklik
                showOptionsDialog(getContext(), mahasiswa);
            }
        });

        return convertView;
    }

    // Metode untuk menampilkan pop-up dengan pilihan
    private void showOptionsDialog(Context context, final Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilih Tindakan");
        builder.setItems(new CharSequence[]{"Detail", "Edit", "Hapus"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //Tampilkan detail mahasiswa
                        showMahasiswaDetailDialog(getContext(), mahasiswa);
                        break;
                    case 1:
                        // Edit mahasiswa
                        showEditMahasiswaDialog(getContext(), mahasiswa);
                        break;
                    case 2:
                        // Hapus mahasiswa
                        showDeleteConfirmationDialog(getContext(), mahasiswa);
                        break;
                }
            }
        });
        builder.show();
    }
    private void showMahasiswaDetailDialog(Context context, Mahasiswa mahasiswa) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("Detail Mahasiswa");
        builder.setMessage("Nama: " + mahasiswa.getNama() + "\nNIM: " + mahasiswa.getNim() +
                "\nJenis Kelamin: " + mahasiswa.getJenisKelamin() +
                "\nPerguruan Tinggi: " + mahasiswa.getPerguruanTinggi() +
                "\nProdi: " + mahasiswa.getProdi());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // Metode untuk menampilkan dialog konfirmasi hapus
    private void showDeleteConfirmationDialog(Context context, final Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Konfirmasi Hapus");
        builder.setMessage("Apakah Anda yakin ingin menghapus mahasiswa ini?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Hapus mahasiswa dari database atau daftar
                deleteMahasiswa(mahasiswa);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // Metode untuk menghapus mahasiswa
        private void deleteMahasiswa(Mahasiswa mahasiswa) {
        // Implementasi untuk menghapus mahasiswa
        // Misalnya, jika menggunakan daftar, Anda dapat menggunakan metode remove()
        remove(mahasiswa);
        // Setelah menghapus, panggil notifyDataSetChanged() untuk memperbarui tampilan
        notifyDataSetChanged();
    }

    // Metode untuk menampilkan dialog edit mahasiswa
    private void showEditMahasiswaDialog(Context context, final Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Mahasiswa");

        // Inflate layout untuk dialog edit mahasiswa
        View view = LayoutInflater.from(context).inflate(R.layout.activity_update_mahasiswa, null);
        builder.setView(view);

        // Temukan elemen-elemen di layout dialog
        final EditText editTextNama = view.findViewById(R.id.editTextNama);
        final EditText editTextNIM = view.findViewById(R.id.editTextNIM);
        final EditText editTextJenisKelamin = view.findViewById(R.id.editTextJenisKelamin);
        final EditText editTextPerguruanTinggi = view.findViewById(R.id.editTextPerguruanTinggi);
        final EditText editTextProdi = view.findViewById(R.id.editTextProdi);

        // Isi nilai awal dari mahasiswa ke dalam elemen-elemen edit text
        editTextNama.setText(mahasiswa.getNama());
        editTextNIM.setText(String.valueOf(mahasiswa.getNim()));
        editTextJenisKelamin.setText(mahasiswa.getJenisKelamin());
        editTextPerguruanTinggi.setText(mahasiswa.getPerguruanTinggi());
        editTextProdi.setText(mahasiswa.getProdi());

        // Tambahkan tombol simpan
        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ambil nilai yang dimasukkan pengguna
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

                // Panggil metode notifyDataSetChanged() untuk memperbarui tampilan
                notifyDataSetChanged();

                // Sembunyikan dialog
                dialog.dismiss();
            }
        });

        // Tambahkan tombol batal
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Sembunyikan dialog
                dialog.dismiss();
            }
        });

        // Tampilkan dialog
        builder.show();
    }
}