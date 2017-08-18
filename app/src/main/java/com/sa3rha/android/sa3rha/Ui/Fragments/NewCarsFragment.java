package com.sa3rha.android.sa3rha.Ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sa3rha.android.sa3rha.Ui.Activities.CategoryActivity;
import com.sa3rha.android.sa3rha.Ui.Activities.OfferActivity;
import com.sa3rha.android.sa3rha.Controller.NewCarAdapter;
import com.sa3rha.android.sa3rha.R;

public class NewCarsFragment extends Fragment implements NewCarAdapter.View_Car_ClickLisener, View.OnClickListener {

    GridLayoutManager gridLayoutManager;
    RecyclerView rv_newCar;
    Button btn_ShowServices, btn_hideServices, btn_insuranceOffer, btn_installmentOffer, btn_shadingOffer;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetBehavior bottomSheetBehavior;
    int screen_width_in_pixel;

    public NewCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_car, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        //bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        btn_ShowServices = (Button) view.findViewById(R.id.BTN_ShowServices);
        btn_ShowServices.setOnClickListener(this);
        findScreenWidth();
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rv_newCar = (RecyclerView) view.findViewById(R.id.RV_newCars);
        rv_newCar.setLayoutManager(gridLayoutManager);
        NewCarAdapter adapter = new NewCarAdapter(getActivity());
        adapter.setViewCarClickLisener(this);
        rv_newCar.setAdapter(adapter);

    }


    public void findScreenWidth() {
        //to make grid layout fit for fo 4 image
        DisplayMetrics DM = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(DM);
        screen_width_in_pixel = DM.widthPixels;

    }

    @Override
    public void onViewCarClickLisener(int position) {
        startActivity(new Intent(getActivity(), CategoryActivity.class));
        getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.BTN_ShowServices) {
            getBottomSheet();
        } else if (id == R.id.BTN_hide) {
            bottomSheetDialog.hide();
        } else if (id == R.id.BTN_offer_insurance) {
            bottomSheetDialog.hide();
            Intent i = new Intent(getActivity(), OfferActivity.class);
            i.putExtra("target_offer", 1);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        } else if (id == R.id.BTN_InstallmentOffer) {
            bottomSheetDialog.hide();
            Intent i = new Intent(getActivity(), OfferActivity.class);
            i.putExtra("target_offer", 2);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        } else if (id == R.id.BTN_shading) {
            bottomSheetDialog.hide();
            Intent i = new Intent(getActivity(), OfferActivity.class);
            i.putExtra("target_offer", 3);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        }

    }

    public void getBottomSheet() {
        View parentView = getActivity().getLayoutInflater().inflate(R.layout.content_bottom_sheet, null);
        bottomSheetDialog.setContentView(parentView);
        //initialize views
        btn_hideServices = (Button) bottomSheetDialog.findViewById(R.id.BTN_hide);
        btn_hideServices.setOnClickListener(this);
        btn_insuranceOffer = (Button) bottomSheetDialog.findViewById(R.id.BTN_offer_insurance);
        btn_insuranceOffer.setOnClickListener(this);
        btn_installmentOffer = (Button) bottomSheetDialog.findViewById(R.id.BTN_InstallmentOffer);
        btn_installmentOffer.setOnClickListener(this);
        btn_shadingOffer = (Button) bottomSheetDialog.findViewById(R.id.BTN_shading);
        btn_shadingOffer.setOnClickListener(this);
        // bottomSheetBehavior
        bottomSheetBehavior = BottomSheetBehavior.from((View) parentView.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
        bottomSheetDialog.show();
    }

}
