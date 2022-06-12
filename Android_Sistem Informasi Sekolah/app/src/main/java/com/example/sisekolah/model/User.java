package com.example.sisekolah.model;

public class User {
    private int id;
    private String username, password, nama, akses;

    public User(int id, String username, String password, String nama, String akses){
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.akses = akses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }
}
