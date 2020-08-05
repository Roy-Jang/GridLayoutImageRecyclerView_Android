package com.rdmchain.gridlayoutimagerecyclerview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("images")
    List<Images> images;

    public String getStatus() {
        return status;
    }

    public List<Images> getImages() {
        return images;
    }
}

class Images {

    @SerializedName("id")
    private int imageId;

    @SerializedName("image_path")
    private String imagePath;

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImagePath() {
        return imagePath;
    }
}
