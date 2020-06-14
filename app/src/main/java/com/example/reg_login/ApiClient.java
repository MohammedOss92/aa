package com.example.reg_login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    public static final String BASE_URL ="http://10.2.2/";
    public static final String BASE_URL ="https://abdallah92.000webhostapp.com/aa/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient()
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.
                    create()).build();

        }

        return retrofit;
    }
}
