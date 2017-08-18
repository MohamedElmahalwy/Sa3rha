package com.sa3rha.android.sa3rha.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sa3rha.android.sa3rha.R;

import butterknife.ButterKnife;

public class SerachAdapter extends RecyclerView.Adapter<SerachAdapter.SerachViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    public SerachAdapter(Context context) {
        this.context=context;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public SerachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.iteam_serach,parent,false);
        return new SerachViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SerachViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class SerachViewHolder extends RecyclerView.ViewHolder{

        public SerachViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
