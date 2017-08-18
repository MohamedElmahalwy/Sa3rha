package com.sa3rha.android.sa3rha.Ui.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sa3rha.android.sa3rha.Ui.Activities.Add_used_car_activity;
import com.sa3rha.android.sa3rha.Controller.OldCarAdapter;
import com.sa3rha.android.sa3rha.R;

public class OldCarsFragment extends Fragment{

    RecyclerView rv_oldCar;
    OldCarAdapter adapter;
    ImageView iv_addToCompare;
    FloatingActionButton fab_addUsedCar;
    Dialog dialog;

    public OldCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_old_car, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        rv_oldCar= (RecyclerView) v.findViewById(R.id.RV_oldCars);
        fab_addUsedCar= (FloatingActionButton) v.findViewById(R.id.FAB_addUsedCar);
        fab_addUsedCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Add_used_car_activity.class));
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });
        rv_oldCar.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
        adapter=new OldCarAdapter(getActivity());
        rv_oldCar.setAdapter(adapter);
    }

    private void addToCompar() {
        dialog = new Dialog(getActivity());
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
