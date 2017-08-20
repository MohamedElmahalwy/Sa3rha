package com.sa3rha.android.sa3rha.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.Models.Brand;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewBrandsAdapter extends RecyclerView.Adapter<NewBrandsAdapter.NewCarAdapterViewHolder> {
    Context context;
    ArrayList<Brand> brands;
    BrandCallBack brandCallBack;


    public NewBrandsAdapter(Context context, ArrayList<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @Override
    public NewCarAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_new_brand, null);
        return new NewCarAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewCarAdapterViewHolder holder, final int position) {
        Picasso.with(context).
                load(Constants.MEDIA_LINK + Constants.BrandsImages+ brands.get(position).getBrandLogo()).into(holder.iv_brandLogo);
        holder.tv_brandName.setText(brands.get(position).getBrandTitle());

        holder.brandItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brandCallBack != null) {
                    brandCallBack.onBrandSelected(brands.get(position).getBrandId(), brands.get(position).getBrandTitle());
                } else {
                    Log.e("null listener", "" + null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    class NewCarAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.brandItemContainer)
        LinearLayout brandItemContainer;
        @BindView(R.id.IV_brandLogo)
        ImageView iv_brandLogo;
        @BindView(R.id.TV_brandLogo)
        TextView tv_brandName;

        public NewCarAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setViewCarClickListener(BrandCallBack brandCallBack) {
        this.brandCallBack = brandCallBack;
    }

    public interface BrandCallBack {
        void onBrandSelected(int brand_Id, String brand_title);
    }
}
