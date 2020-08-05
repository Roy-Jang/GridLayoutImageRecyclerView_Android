package com.rdmchain.gridlayoutimagerecyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("get_images.php")
    Call<List<DataResponse>> getImages(@Query("page_number") int page, @Query("item_count") int itmes);
}
