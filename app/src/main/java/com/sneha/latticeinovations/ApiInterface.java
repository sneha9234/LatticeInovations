package com.sneha.latticeinovations;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("register.php")
    Call<user> performRegistration(@Query("name") String name, @Query("address") String address, @Query("email") String email, @Query("phone") String phone, @Query("password") String password, @Query("location") String location);

    @GET("video.php")
    Call<List<user>> getData();
}
