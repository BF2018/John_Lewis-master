package com.example.john_lewis.ui.home.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john_lewis.R;
import com.example.john_lewis.common.Constants;
import com.example.john_lewis.data.Product;
import com.example.john_lewis.data.Products;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder> {

    private Products products;
    ColorSwatchAdapter colorAdaptor;
    RecyclerView.LayoutManager colorOptionlayoutManager;
    Context context;

    public HomeAdapter(Products products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_listing, viewGroup, false);

        return new HomeAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterViewHolder homeAdapterViewHolder, int i) {
             homeAdapterViewHolder.bind(products.getProducts().get(i));

    }

    @Override
    public int getItemCount() {
        return products.getProducts().size();
    }

    public class HomeAdapterViewHolder extends RecyclerView.ViewHolder {



       @BindView(R.id.tv_product_title) TextView productTitle;
        @BindView(R.id.tv_product_id) TextView productId;
        @BindView(R.id.tv_price_now)
        TextView priceNow;
        @BindView(R.id.ivAlbumArt) ImageView productImage;
        @BindView(R.id.colors_watch_recycler_view)
        RecyclerView colorRecycleView;


        public HomeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        void bind(Product product){

            productId.setText("Id " + product.getProductId());
            productTitle.setText(product.getTitle());

            priceNow.setText(product.getPrice().getPriceLable());

            if(product.getColorSwatches().size()>0){
                addView(product);
            }


            if (product.getImage() != null) {
                Picasso.get()
                        .load(Constants.HTTPS +product.getImage())
                        .fit()
                        .into(productImage);
            }


        }
        void addView(Product product){

            colorOptionlayoutManager = new GridLayoutManager(itemView.getContext(), 3, GridLayoutManager.VERTICAL, false);
            colorRecycleView.setLayoutManager(colorOptionlayoutManager);
            colorRecycleView.addItemDecoration(new CustomGrid(3, dpToPx(5, itemView.getContext()), true));
            colorRecycleView.setItemAnimator(new DefaultItemAnimator());
            colorAdaptor = new ColorSwatchAdapter(product.getColorSwatches());
            colorRecycleView.setAdapter(colorAdaptor);
            colorRecycleView.setVisibility(View.VISIBLE);

        }

    }

    private int dpToPx(int dp, Context context) {

        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
