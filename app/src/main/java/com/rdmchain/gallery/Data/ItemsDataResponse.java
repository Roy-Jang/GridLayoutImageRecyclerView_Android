package com.rdmchain.gallery.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsDataResponse {

    @SerializedName("items")
    List<Items> items;

    @SerializedName("current_page")
    private int currentPage;

    @SerializedName("next_page")
    private int nextPage;

    @SerializedName("total_page")
    private int totalPage;

    @SerializedName("total")
    private int total;

    public List<Items> getItems() {
        return items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotal() {
        return total;
    }
}