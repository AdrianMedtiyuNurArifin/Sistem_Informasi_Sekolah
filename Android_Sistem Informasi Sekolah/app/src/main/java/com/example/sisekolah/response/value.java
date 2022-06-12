package com.example.sisekolah.response;

import com.example.sisekolah.model.Pengumuman;

import java.util.List;

public class value {
    String message;
    List<Pengumuman> pengumumen;

    public String getMessage() {
        return message;
    }

    public List<Pengumuman> getPengumumen() {
        return pengumumen;
    }
}
