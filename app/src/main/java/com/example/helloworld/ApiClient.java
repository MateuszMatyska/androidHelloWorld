package com.example.helloworld;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient instance = null;
    private ApiConnection myApi;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConnection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiConnection.class);
    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public ApiConnection getMyApi() {
        return myApi;
    }
}
