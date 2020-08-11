package com.rdmchain.gallery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.rdmchain.gallery.Adapter.AdapterItems;
import com.rdmchain.gallery.Api.ApiClient;
import com.rdmchain.gallery.Api.ApiInterface;
import com.rdmchain.gallery.Data.Items;
import com.rdmchain.gallery.Data.ItemsDataResponse;
import com.rdmchain.gallery.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterItems.OnLoadMoreListener {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ApiInterface apiInterface;
    private NestedScrollView mNestedScrollView;

    // 리스트 관련
    private AdapterItems mAdapter;
    private List<Items> itemsList;
    private int nextPage, totalCount;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // View
        mNestedScrollView = findViewById(R.id.nestedScrollView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mProgressBar = findViewById(R.id.progressBar);

        // 리스트 기본값
        itemsList = new ArrayList<Items>();
        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new AdapterItems(this, itemsList, mContext, mProgressBar);
        mRecyclerView.setAdapter(mAdapter);
        nextPage = 1;
        totalCount = 0;
        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);

        // 처음 페이지
        callApi(false);

        // 추가 페이지
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) && scrollY > oldScrollY) {
                        mAdapter.showLoading();
                    }
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        callApi(true);
    }

    private void callApi(final boolean isMore) {
        Call <ItemsDataResponse> call = apiInterface.getItems(nextPage);
        call.enqueue(new Callback<ItemsDataResponse>() {
            @Override
            public void onResponse(Call<ItemsDataResponse> call, Response<ItemsDataResponse> response) {
                if (response.body().getItems().size() > 0) {
                    if (isMore) {
                        try {
                            Thread.sleep(1500);
                            mAdapter.dismissLoading();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter.addItems(response.body().getItems());
                    nextPage = response.body().getNextPage();
                    totalCount = response.body().getTotal();
                    if (nextPage == 0) {
                        mAdapter.setMore(false);
                    }
                } else {
                    // 없을 경우
                    mRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemsDataResponse> call, Throwable t) {
                // 오류
                Log.e("jang", "" + t);
            }
        });
    }
}
