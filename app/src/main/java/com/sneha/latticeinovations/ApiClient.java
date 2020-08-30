package com.sneha.latticeinovations;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://10.0.2.2/restaurant/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            //   Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
