package com.example.john_lewis.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Price {
    @SerializedName("was")
    @Expose
    private String was;
    @SerializedName("then1")
    @Expose
    private String then1;
    @SerializedName("then2")
    @Expose
    private String then2;
    @SerializedName("now")
    @Expose
    private Object now;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("currency")
    @Expose
    private String currency;

    private String discounted;

    private Integer finalNowPrice;


    private String showPercDscount;


    private String priceLable;

    public String getWas() {
        return was;
    }

    public void setWas(String was) {
        this.was = was;
    }

    public String getThen1() {
        return then1;
    }

    public void setThen1(String then1) {
        this.then1 = then1;
    }

    public String getThen2() {
        return then2;
    }

    public void setThen2(String then2) {
        this.then2 = then2;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCurrency() {

        if (currency.equalsIgnoreCase("GBP")) {
            return "£";
        } else {
            return currency;
        }


    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getNow() {

        return now;
    }

    public void setDiscounted(Object nowPrice) {

        this.now = nowPrice;
    }

    public String getDiscounted() {
        String wasPrice = getWas();
        Object nowPrice = getNow();

        double was = Double.valueOf(wasPrice);

        Double now = null;
        if (nowPrice instanceof String) {
            now = Double.valueOf(String.valueOf(nowPrice));

            discounted = String.valueOf(was - now);
        } else {
            discounted = String.valueOf(was - Double.valueOf(((LinkedTreeMap) nowPrice).get("to").toString()));
        }

        return discounted;
    }

    public int getFinalNowPrice() {
        Object nowPrice = getNow();

        Double finalNow = null;

        if (nowPrice instanceof String) {
            finalNow = Double.valueOf(String.valueOf(nowPrice));
            finalNowPrice = Integer.valueOf(finalNow.intValue());
        } else {
            finalNow = Double.valueOf(((LinkedTreeMap) nowPrice).get("to").toString());
            finalNowPrice = Integer.valueOf(finalNow.intValue());

        }

        return finalNowPrice;
    }

    public void setFinalNowPrice(int finalNowPrice) {
        this.finalNowPrice = finalNowPrice;
    }


    public String getPriceLable() {

        String price;
        if (getFinalNowPrice() < 10) {
            price = String.valueOf(getFinalNowPrice() + ".00");
        } else
            price = String.valueOf(getFinalNowPrice());


        if (!getThen2().equals("") || !getThen2().isEmpty()) {

            // showWasThenNow  “Was £x.xx, then £y.yy, now £z.zzz” with then2
            priceLable = "Was " + getCurrency() + filter(getWas()) + ", then " + getCurrency() + filter(getThen2()) + " now " + getCurrency() + price;
        } else if (!getThen1().equals("") || !getThen1().isEmpty()) {
            // showWasThenNow “Was £x.xx, then £y.yy, now £z.zzz” with then1
            priceLable = "Was " + getCurrency() + filter(getWas()) + ", then " + getCurrency() + filter(getThen1()) + ", now " + getCurrency() + price;
        } else if (getThen2().equals("") && getThen1().equals("")) {
            // showWasNow returns  “Was £x.xx, now £y.yyy”.
            priceLable = "Was £" + filter(getWas()) + " ,now " + getCurrency() + price;

        } else {
            priceLable = getShowPercDscount();
        }

        return priceLable;
    }

    public void setPriceLable(String priceLable) {
        this.priceLable = priceLable;
    }

    // return “x% off - now £y.yy”
    public String getShowPercDscount() {

        double showPercDiscount = (Double.parseDouble(getDiscounted()) / Double.valueOf(getWas())) * 100;

        return showPercDscount = Integer.toString((int) showPercDiscount) + "% off" + " - now" + getCurrency() + getFinalNowPrice();
    }

    public void setShowPercDscount(String showPercDscount) {
        this.showPercDscount = showPercDscount;
    }

    public String filter(String decimals) {

        Double d = Double.valueOf(String.valueOf(decimals));
        return decimals = Integer.valueOf(d.intValue()).toString();

    }

}
