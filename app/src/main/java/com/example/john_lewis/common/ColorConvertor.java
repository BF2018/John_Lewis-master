package com.example.john_lewis.common;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.Nullable;

public class ColorConvertor {

    private Map<String, String> colorMap = new HashMap<>();

    String colorValue;
    String defaultColor;

    String color;

    public ColorConvertor(@Nullable String color) {
        this.color = color;
        colorMap.put("black", "000000");
        colorMap.put("white", "FFFFFF");
        colorMap.put("red", "FF0000");
        colorMap.put("blue", "0000FF");
        colorMap.put("cyan", "00FFFF");
        colorMap.put("yellow", "FFFF00");
        colorMap.put("magenta", "8B008B");
        colorMap.put("dark grey", "A9A9A9");
        colorMap.put("gray", "808080");
        colorMap.put("green", "008000");
        colorMap.put("burgundy", "8B0000");
        colorMap.put("dark teal", "008080");
        colorMap.put("royal blue", "00008B");
        colorMap.put("navy", "000080");
        colorMap.put("berry", "800080");
        colorMap.put("dark mauve", "4B0082");

        defaultColor = "#FFE4B5";

    }

    public String getColorValue() {

        try {
            colorValue = colorMap.get(color.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();

        }


        if (colorValue != null) {
            return "#" + colorValue;
        } else
            return defaultColor;
    }


    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }


}
