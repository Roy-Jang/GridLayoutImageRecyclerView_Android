package com.rdmchain.gallery.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // public static final String baseUrl = "http://129.0.0.1:8080/galleryapp/scripts/";
    public static final String baseUrl = "https://www.luxtyl.com";
    public static Retrofit retrofit = null;

    public static Retrofit getApiclient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
