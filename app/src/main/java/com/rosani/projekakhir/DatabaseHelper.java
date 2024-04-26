package com.rosani.projekakhir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "SignLog.db";
    private static final int DATABASE_VERSION = 1;
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, "SignLog.db", null, 1);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase MyDatabase) {
//        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
//    }

    // Nama tabel dan kolom
    public static final String TABLE_NAME = "mahasiswa";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";
    public static final String COLUMN_PERGURUAN_TINGGI = "perguruan_tinggi";
    public static final String COLUMN_PRODI = "prodi";

    // Query untuk membuat tabel
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NIM + " INTEGER PRIMARY KEY," +
                    COLUMN_NAMA + " TEXT," +
                    COLUMN_JENIS_KELAMIN + " TEXT," +
                    COLUMN_PERGURUAN_TINGGI + " TEXT," +
                    COLUMN_PRODI + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

// Metode untuk menambahkan data Mahasiswa
public void addMahasiswa (Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_JENIS_KELAMIN, mahasiswa.getJenisKelamin());
        values.put(COLUMN_PERGURUAN_TINGGI, mahasiswa.getPerguruanTinggi());
        values.put(COLUMN_PRODI, mahasiswa.getProdi());

        db.insert(TABLE_NAME, null, values);
        db.close();
}

// Metode untuk mendapatkan semua data Mahasiswa
public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NIM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_JENIS_KELAMIN)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PERGURUAN_TINGGI)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PRODI))
        );
        mahasiswaList.add(mahasiswa);
        } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return mahasiswaList;
        }

// Metode untuk mengupdate data Mahasiswa
public int updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_JENIS_KELAMIN, mahasiswa.getJenisKelamin());
        values.put(COLUMN_PERGURUAN_TINGGI, mahasiswa.getPerguruanTinggi());
        values.put(COLUMN_PRODI, mahasiswa.getProdi());

        return db.update(TABLE_NAME, values, COLUMN_NIM + " = ?",
        new String[]{String.valueOf(mahasiswa.getNim())});
        }

// Metode untuk menghapus data Mahasiswa
public void deleteMahasiswa(int nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NIM + " = ?",
        new String[]{String.valueOf(nim)});
        db.close();
        }







//    @Override
//    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists users");
//    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
