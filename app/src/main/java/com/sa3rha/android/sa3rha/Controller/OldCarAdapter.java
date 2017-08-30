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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sa3rha.android.sa3rha.Models.UsedCar;
import com.sa3rha.android.sa3rha.Ui.Activities.OldCarDetailsActivity;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Utilities.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldCarAdapter extends RecyclerView.Adapter<OldCarAdapter.OldCarViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<UsedCar> usedCarArrayList;
    ArrayList<String> usedCarIdArrayList;
    RequestQueue requestQueue;
    private Dialog dialog;

    public OldCarAdapter(Context context, ArrayList<UsedCar> usedCarArrayList) {
        this.usedCarArrayList = usedCarArrayList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OldCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.iteam_old_car, parent, false);
        requestQueue = Volley.newRequestQueue(context);
        return new OldCarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OldCarViewHolder holder, final int position) {
        holder.TV_carName.setText(usedCarArrayList.get(position).getCarName());
        holder.TV_carPrice.setText(usedCarArrayList.get(position).getCarPrice());
        Picasso.with(context).
                load(Constants.MEDIA_LINK + Constants.CarsImages + usedCarArrayList.get(position).getCarImage()).into(holder.iv_CarIamge);
        holder.iv_CarIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OldCarDetailsActivity.class);
                i.putExtra("carId", usedCarArrayList.get(position).getUsedId());
                ((Activity) context).overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);

                context.startActivity(i);
            }
        });

        holder.iv_addToCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addtoCompare();
            }
        });


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

    @Override
    public int getItemCount() {
        return usedCarArrayList.size();
    }

    public class OldCarViewHolder extends RecyclerView.ViewHolder {
        TextView TV_carName = (TextView) itemView.findViewById(R.id.TV_carName);
        TextView TV_carPrice = (TextView) itemView.findViewById(R.id.TV_carPrice);
        @BindView(R.id.IV_carImage)
        ImageView iv_CarIamge;
        @BindView(R.id.IV_addToCompare)
        ImageView iv_addToCompare;
        Dialog dialog;

        public OldCarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
