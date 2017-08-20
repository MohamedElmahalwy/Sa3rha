package com.sa3rha.android.sa3rha.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.Ui.Activities.OldCarDetailsActivity;
import com.sa3rha.android.sa3rha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldCarAdapter extends RecyclerView.Adapter<OldCarAdapter.OldCarViewHolder>{

    Context context;
    LayoutInflater layoutInflater;

    public OldCarAdapter(Context context) {
        this.context = context;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OldCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.iteam_old_car,parent,false);
        return new OldCarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OldCarViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class OldCarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_carImage)
        ImageView iv_CarIamge;
        @BindView(R.id.IV_addToCompare)
        ImageView iv_addToCompare;
        Dialog dialog;
        public OldCarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            iv_CarIamge.setOnClickListener(this);
            iv_addToCompare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id=v.getId();
            Activity activity = (Activity) context;
            if(id==R.id.IV_carImage){
                activity.startActivity(new Intent(context,OldCarDetailsActivity.class));
                activity.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }else if(id==R.id.IV_addToCompare){
                addtoCompare();
            }
        }

        private void addtoCompare() {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_add_to_combare);
            //set background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btn_continue = (Button) dialog.findViewById(R.id.BTN_continue);

            // to set width and height
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
            btn_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

}