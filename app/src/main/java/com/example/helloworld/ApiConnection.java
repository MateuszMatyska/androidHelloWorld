package com.example.helloworld;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConnection {
    String BASE_URL = "https://danepubliczne.imgw.pl/api/data/";

    @GET("synop")
    Call<List<City>> getCities();

    @GET("synop/id")
    Call<Weather> getWeather(@Query("id") int id);
}
