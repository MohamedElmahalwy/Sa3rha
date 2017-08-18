package com.sa3rha.android.sa3rha.Controller;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;

import com.sa3rha.android.sa3rha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sa3rha.android.sa3rha.R.id.IV_agentLogo;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder>{

    private final int target_offer;
    Context context;
    LayoutInflater layoutInflater;
    Dialog orderDialog;


    public OffersAdapter(Context context, int target_offer) {
        this.context = context;
        this.target_offer=target_offer;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.iteam_offer_activity,parent,false);
        return new OfferViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.TV_agentName)
        TextView tv_agentName;
        @BindView(R.id.TV_offer)
        TextView tv_agentOffer;
        @BindView(IV_agentLogo)
        ImageView iv_agentLogo;
        public OfferViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            tv_agentName.setOnClickListener(this);
            tv_agentOffer.setOnClickListener(this);
            iv_agentLogo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id=v.getId();
            if (id== IV_agentLogo||id==R.id.TV_agentName||id==R.id.TV_offer){
                orderDialog = new Dialog(context);
                orderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                orderDialog.setContentView(R.layout.dialog_make_offer_activity);
                //set background
                orderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btn_makeOrder = (Button) orderDialog.findViewById(R.id.BTN_makeOrder);
                ImageView iv_clear=(ImageView)orderDialog.findViewById(R.id.IV_clear);
                TextView tv_dialogTitle=(TextView)orderDialog.findViewById(R.id.TV_dialogTitle);
                //set dialog title
                if(target_offer==1){
                    tv_dialogTitle.setText(R.string.sendOrderToInsuranceOffer);
                }else if(target_offer==2) {
                    tv_dialogTitle.setText(R.string.sendOrderToInstallmentOffer);
                }
                else if(target_offer==3) {
                    tv_dialogTitle.setText(R.string.sendOrderToShadingOffer);
                }
                // to set width and height
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom( orderDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                orderDialog.getWindow().setAttributes(lp);
                btn_makeOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderDialog.dismiss();
                    }
                });
                iv_clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderDialog.dismiss();
                    }
                });
                orderDialog.show();
            }
        }
    }
}
