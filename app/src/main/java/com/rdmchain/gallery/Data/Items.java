package com.rdmchain.gallery.Data;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("id")
    private String id;

    @SerializedName("shop_id")
    private String shopId;

    @SerializedName("os")
    private String os;

    @SerializedName("item_status")
    private String itemStatus;

    @SerializedName("item_brand")
    private String itemBrand;

    @SerializedName("item_category")
    private String itemCategory;

    @SerializedName("item_name")
    private String itemName;

    @SerializedName("item_price")
    private String itemPrice;

    @SerializedName("item_memo")
    private String itemMemo;

    @SerializedName("item_img1")
    private String itemImg1;

    @SerializedName("item_img2")
    private String itemImg2;

    @SerializedName("item_img3")
    private String itemImg3;

    @SerializedName("item_img4")
    private String itemImg4;

    @SerializedName("item_img5")
    private String itemImg5;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public Items(String id, String shopId, String os, String itemStatus, String itemBrand, String itemCategory, String itemName, String itemPrice, String itemMemo, String itemImg1, String itemImg2, String itemImg3, String itemImg4, String itemImg5, String createdAt, String updatedAt) {
        this.id = id;
        this.shopId = shopId;
        this.os = os;
        this.itemStatus = itemStatus;
        this.itemBrand = itemBrand;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemMemo = itemMemo;
        this.itemImg1 = itemImg1;
        this.itemImg2 = itemImg2;
        this.itemImg3 = itemImg3;
        this.itemImg4 = itemImg4;
        this.itemImg5 = itemImg5;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getShopId() {
        return shopId;
    }

    public String getOs() {
        return os;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemMemo() {
        return itemMemo;
    }

    public String getItemImg1() {
        return itemImg1;
    }

    public String getItemImg2() {
        return itemImg2;
    }

    public String getItemImg3() {
        return itemImg3;
    }

    public String getItemImg4() {
        return itemImg4;
    }

    public String getItemImg5() {
        return itemImg5;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
