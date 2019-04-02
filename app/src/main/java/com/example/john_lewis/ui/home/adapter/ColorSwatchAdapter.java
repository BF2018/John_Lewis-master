package com.example.john_lewis.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john_lewis.R;
import com.example.john_lewis.common.ColorConvertor;
import com.example.john_lewis.data.ColorSwatch;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorSwatchAdapter extends RecyclerView.Adapter<ColorSwatchAdapter.ColorSwatchViewHolder> {

    List<ColorSwatch> colorSwatches;
    private ColorConvertor colorConvertor;


    public ColorSwatchAdapter(List<ColorSwatch> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    @NonNull
    @Override
    public ColorSwatchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.colorswatch_list, viewGroup, false);

        return new ColorSwatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorSwatchViewHolder colorViewHolder, int i) {
        colorViewHolder.bind(colorSwatches.get(i));
    }

    @Override
    public int getItemCount() {
        return colorSwatches.size();
    }

    public class ColorSwatchViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.color_point)
        TextView colorPoint;
        @BindView(R.id.sku_id)
        TextView skuId;
        @BindView(R.id.rgb_color)
        TextView rgbColor;


        public ColorSwatchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(ColorSwatch colorSwatch) {
            if (colorSwatch.getColor() != null && colorSwatch.getBasicColor() != null) {
                colorPoint.setText(colorSwatch.getColor());
                skuId.setText(colorSwatch.getSkuId());

                colorConvertor = new ColorConvertor(colorSwatch.getBasicColor());
                rgbColor.setText(colorConvertor.getColorValue());
            }
        }

    }
}

