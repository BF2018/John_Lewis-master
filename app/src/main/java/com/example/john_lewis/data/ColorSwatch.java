package com.example.john_lewis.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ColorSwatch {
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("basicColor")
    @Expose
    private String basicColor;
    @SerializedName("colorSwatchUrl")
    @Expose
    private String colorSwatchUrl;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("isAvailable")
    @Expose
    private Boolean isAvailable;
    @SerializedName("skuId")
    @Expose
    private String skuId;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBasicColor() {
        return basicColor;
    }

    public void setBasicColor(String basicColor) {
        this.basicColor = basicColor;
    }

    public String getColorSwatchUrl() {
        return colorSwatchUrl;
    }

    public void setColorSwatchUrl(String colorSwatchUrl) {
        this.colorSwatchUrl = colorSwatchUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }


}
