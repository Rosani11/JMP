package com.rosani.projekakhir;

public class Mahasiswa {
    private int nim;
    private String nama;
    private String jenisKelamin;
    private String perguruanTinggi;
    private String prodi;

    public Mahasiswa(int nim, String nama, String jenisKelamin, String perguruanTinggi, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.perguruanTinggi = perguruanTinggi;
        this.prodi = prodi;
    }

    public int getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getPerguruanTinggi() {
        return perguruanTinggi;
    }

    public String getProdi() {
        return prodi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setPerguruanTinggi(String perguruanTinggi) {
        this.perguruanTinggi = perguruanTinggi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
}
