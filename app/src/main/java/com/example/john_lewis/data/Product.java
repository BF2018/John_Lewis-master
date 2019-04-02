package com.example.john_lewis.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("averageRating")
    @Expose
    private Double averageRating;
    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("defaultSkuId")
    @Expose
    private String defaultSkuId;
    @SerializedName("colorSwatches")
    @Expose
    private List<ColorSwatch> colorSwatches = null;

    protected Product(Parcel in) {
        productId = in.readString();
        type = in.readString();
        title = in.readString();
        code = in.readString();
        if (in.readByte() == 0) {
            averageRating = null;
        } else {
            averageRating = in.readDouble();
        }
        if (in.readByte() == 0) {
            reviews = null;
        } else {
            reviews = in.readInt();
        }
        image = in.readString();
        defaultSkuId = in.readString();
        colorSwatches = in.createTypedArrayList(ColorSwatch.CREATOR);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDefaultSkuId() {
        return defaultSkuId;
    }

    public void setDefaultSkuId(String defaultSkuId) {
        this.defaultSkuId = defaultSkuId;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatch> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(code);
        if (averageRating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(averageRating);
        }
        if (reviews == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(reviews);
        }
        dest.writeString(image);
        dest.writeString(defaultSkuId);
        dest.writeTypedList(colorSwatches);
    }
}
