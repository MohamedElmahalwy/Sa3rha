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

import com.sa3rha.android.sa3rha.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCarAdapter extends RecyclerView.Adapter<NewCarAdapter.NewCarAdapterViewHolder >{

    Context context;
    LayoutInflater layoutInflater;
    boolean colored=false;
    private View_Car_ClickLisener view_car_clickLisener;


    public NewCarAdapter(Context context) {
        this.context = context;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public NewCarAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=layoutInflater.inflate(R.layout.iteam_new_car,parent,false);

        return new NewCarAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewCarAdapterViewHolder holder, final int position) {

        Picasso.with(context).load(R.mipmap.ic_launcher_round).into(holder.iv_carMark);
        holder.linear_container_newCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view_car_clickLisener!=null){
                    view_car_clickLisener.onViewCarClickLisener(position);
                }
                else{
                    Log.e("null listener", "" + null);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return 28;
    }

    class NewCarAdapterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.IV_carMark)
        ImageView iv_carMark;
        @BindView(R.id.TV_CarName)
        TextView tv_carName;
        @BindView(R.id.Linear_container_newCars)
        LinearLayout linear_container_newCars;

        public NewCarAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public  interface View_Car_ClickLisener{
        void onViewCarClickLisener(int position);
    }
    public void setViewCarClickLisener(View_Car_ClickLisener view_car_clickLisener){
        this.view_car_clickLisener=view_car_clickLisener;
    }
}
