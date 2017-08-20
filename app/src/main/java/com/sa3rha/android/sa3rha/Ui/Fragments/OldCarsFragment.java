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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sa3rha.android.sa3rha.Models.UsedCar;
import com.sa3rha.android.sa3rha.Ui.Activities.Add_used_car_activity;
import com.sa3rha.android.sa3rha.Controller.OldCarAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OldCarsFragment extends Fragment {

    RecyclerView rv_oldCar;
    OldCarAdapter adapter;
    ImageView iv_addToCompare;
    FloatingActionButton fab_addUsedCar;
    Dialog dialog;
    RequestQueue requestQueue;
    ArrayList<UsedCar> usedCarArrayList;

    public OldCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_old_car, container, false);
        usedCarArrayList = new ArrayList<>();
        init(v);
        //using fixed id
        getOldCars(2);
        return v;
    }

    private void init(View v) {
        rv_oldCar = (RecyclerView) v.findViewById(R.id.RV_oldCars);
        fab_addUsedCar = (FloatingActionButton) v.findViewById(R.id.FAB_addUsedCar);
        fab_addUsedCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Add_used_car_activity.class));
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });
        rv_oldCar.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));

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

    //*************************************************************************************************************//
//using fixed id
    public void getOldCars(int id) {
        String url = Constants.API_LINK + "usedCars?brandId=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject usedCar = jsonArray.getJSONObject(i);
                        String carName = usedCar.getString("subBrandTitle");
                        String usedCarPrice = usedCar.getString("usedCarPrice");
                        String usedCarImgs = usedCar.getString("carImgs");
                        String img = null;
                        if (!usedCarImgs.contains("null")) {

                            String[] items = usedCarImgs.split(",");
//                        for (String item : items)
//                        {
//                            System.out.println("item = " + item);
//                        }
                            img = items[0];

                            System.out.println(items[0]);

                        }
                        UsedCar usedCar1 = new UsedCar(carName, usedCarPrice, img);
                        usedCarArrayList.add(usedCar1);


                    }
                    adapter = new OldCarAdapter(getActivity(), usedCarArrayList);
                    rv_oldCar.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "error");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
