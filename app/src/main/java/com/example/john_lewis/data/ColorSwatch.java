package com.example.john_lewis.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ColorSwatch implements Parcelable {
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

    protected ColorSwatch(Parcel in) {
        color = in.readString();
        basicColor = in.readString();
        colorSwatchUrl = in.readString();
        imageUrl = in.readString();
        byte tmpIsAvailable = in.readByte();
        isAvailable = tmpIsAvailable == 0 ? null : tmpIsAvailable == 1;
        skuId = in.readString();
    }

    public static final Creator<ColorSwatch> CREATOR = new Creator<ColorSwatch>() {
        @Override
        public ColorSwatch createFromParcel(Parcel in) {
            return new ColorSwatch(in);
        }

        @Override
        public ColorSwatch[] newArray(int size) {
            return new ColorSwatch[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeString(basicColor);
        dest.writeString(colorSwatchUrl);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (isAvailable == null ? 0 : isAvailable ? 1 : 2));
        dest.writeString(skuId);
    }
}
