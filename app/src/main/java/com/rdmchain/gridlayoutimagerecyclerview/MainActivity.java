package com.rdmchain.gridlayoutimagerecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ApiInterface apiInterface;
    private RecyclerAdapter adapter;

    private int pageNumber = 1;
    private int item_count = 10;
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

//        progressBar.setVisibility(View.VISIBLE);
//        String response = "" +
//                "{\"status\":\"ok\"," +
//                    "\"images\":[" +
//                        "{\"id\":\"1\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"2\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"3\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"4\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"5\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"6\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"7\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"8\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"9\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                        "{\"id\":\"10\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}" +
//                    "]" +
//                "}";
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = (JsonObject)  parser.parse(response);
//        JsonElement status = jsonObject.get("status");
//        JsonArray jsonArray = jsonObject.getAsJsonArray("images");
//
//        Gson gson = new Gson();
//        List<Images> images = gson.fromJson(jsonArray, new TypeToken<List<Images>>(){}.getType());
//        adapter = new RecyclerAdapter(images, MainActivity.this);
//        recyclerView.setAdapter(adapter);
//        Toast.makeText(MainActivity.this, "FirstPage is loaded", Toast.LENGTH_SHORT).show();
//        progressBar.setVisibility(View.GONE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previous_total) {
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold)) {
                        pageNumber++;
                        preformPagination();
                        isLoading = true;
                    }
                }
            }
        });


        // API 호출
        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>> call = apiInterface.getImages(pageNumber, item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Call<List<DataResponse>> call, Response<List<DataResponse>> response) {

                if (response.body().get(0).getStatus().equals("ok")) {

                    List<Images> images = response.body().get(1).getImages();
                    adapter = new RecyclerAdapter(images, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "FirstPage is loaded", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(MainActivity.this, "No more Images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {

            }
        });
    }

    private void preformPagination() {
//        progressBar.setVisibility(View.VISIBLE);
//        String response = "" +
//                "{\"status\":\"ok\"," +
//                "\"images\":[" +
//                "{\"id\":\"11\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"12\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"13\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"14\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"15\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"16\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"17\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"18\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"19\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}," +
//                "{\"id\":\"20\",\"image_path\":\"https:\\/\\/cdn.pixabay.com\\/photo\\/2018\\/09\\/06\\/19\\/53\\/mushroom-3659165__340.jpg\"}" +
//                "]" +
//                "}";
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = (JsonObject)  parser.parse(response);
//        JsonElement status = jsonObject.get("status");
//        JsonArray jsonArray = jsonObject.getAsJsonArray("images");
//
//        Gson gson = new Gson();
//        List<Images> images = gson.fromJson(jsonArray, new TypeToken<List<Images>>(){}.getType());
//        adapter.addImages(images);
//        Toast.makeText(MainActivity.this, "Page " + pageNumber +" is loaded", Toast.LENGTH_SHORT).show();
//        progressBar.setVisibility(View.GONE);

        // API 호출
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>> call = apiInterface.getImages(pageNumber, item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Call<List<DataResponse>> call, Response<List<DataResponse>> response) {

                if (response.body().get(0).getStatus().equals("ok")) {

                    List<Images> images = response.body().get(1).getImages();
                    adapter.addImages(images);
                    Toast.makeText(MainActivity.this, "Page " + pageNumber +" is loaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No more Images", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {

            }
        });
    }
}
