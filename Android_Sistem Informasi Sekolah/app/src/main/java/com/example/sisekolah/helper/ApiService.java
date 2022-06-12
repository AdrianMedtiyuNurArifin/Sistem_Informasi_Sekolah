package com.example.sisekolah.helper;

import com.example.sisekolah.model.Announcement;
import com.example.sisekolah.model.Pengumuman;
import com.example.sisekolah.response.LoginResponse;
import com.example.sisekolah.response.PengumumanResponse;
import com.example.sisekolah.response.RegisterResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("api.php?apicall=signup")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("akses") String akses
    );

    @FormUrlEncoded
    @POST("api.php?apicall=login")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    //tanpa @FormUrlEncoded jika @GET
    @Headers("Accept: application/json")
    @GET("api.php?apicall=getPengumuman")
    //Call<PengumumanResponse> getPengumuman();
    Call<ArrayList<Announcement>> getPengumuman();
}
