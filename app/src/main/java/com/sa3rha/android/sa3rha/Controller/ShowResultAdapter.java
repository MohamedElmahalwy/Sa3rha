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

import com.sa3rha.android.sa3rha.Ui.Activities.OfferActivity;
import com.sa3rha.android.sa3rha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowResultAdapter extends RecyclerView.Adapter<ShowResultAdapter.ShowResultViewHOlder> {

    Context context;
    LayoutInflater layoutInflater;
    //
    Dialog dialog;

    public ShowResultAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ShowResultViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.iteam_show_result_view, parent, false);
        return new ShowResultViewHOlder(v);
    }

    @Override
    public void onBindViewHolder(ShowResultViewHOlder holder, int position) {

        holder.iv_addToCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addedToCombare();

            }
        });

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ShowResultViewHOlder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_addToCompare)
        ImageView iv_addToCompare;
        @BindView(R.id.BTN_insurance_offer)
        Button btn_insurance_offer;
        @BindView(R.id.BTN_installment_offer)
        Button btn_installment_offer;
        @BindView(R.id.BTN_shading_offer)
        Button btn_shading_offer;


        public ShowResultViewHOlder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btn_installment_offer.setOnClickListener(this);
            btn_insurance_offer.setOnClickListener(this);
            btn_shading_offer.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int id=v.getId();
            Activity activity = (Activity) context;
            if(id==R.id.BTN_insurance_offer){
                Intent i=new Intent(context, OfferActivity.class);
               //1 mean insurance_offer
                i.putExtra("target_offer",1);
                context.startActivity(i);
                activity.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }else if(id==R.id.BTN_installment_offer){
                Intent i=new Intent(context, OfferActivity.class);
                //1 mean insurance_offer
                i.putExtra("target_offer",2);
                context.startActivity(i);
                activity.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            } else if(id==R.id.BTN_shading_offer){
                Intent i=new Intent(context, OfferActivity.class);
                //1 mean insurance_offer
                i.putExtra("target_offer",3);
                context.startActivity(i);
                activity.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        }
    }
        private void addedToCombare() {
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
