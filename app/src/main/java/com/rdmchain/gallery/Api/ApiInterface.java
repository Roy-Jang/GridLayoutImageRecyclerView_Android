package com.rdmchain.gallery.Api;

import com.rdmchain.gallery.Data.ItemsDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"X-Luxtyl-Token: JangApiTest",
            "X-Luxtyl-Os: AOS"})
    @GET("/api/items/recommend/{page}")
    Call<ItemsDataResponse> getItems(@Path("page") int page);
}
