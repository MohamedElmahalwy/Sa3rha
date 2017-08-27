package com.sa3rha.android.sa3rha.Ui.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sa3rha.android.sa3rha.Controller.ExpandableListViewAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity {
    @BindView(R.id.IV_backHome)
    ImageView iv_backHome;
    TextView tvCarName;
    ActionBarDrawerToggle toggle;
    @BindView(R.id.expandable)
    ExpandableListView expandableListView;
    ExpandableListViewAdapter adapter;
    private ArrayList<String> expandableListTitle;
    int brand_Id;
    String brand_Title;
    RequestQueue requestQueue;
    String bTitle = "";
    int bId;
    String sub = "";
    List<String> subBrandArr;
    List<String> subBrandCarArr;
    public static int countBrand = 0;
    HashMap<String, List<String>> expandableListDetail;
    ArrayList<String> dataStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        requestQueue = Volley.newRequestQueue(this);
        init();
        expandableListDetail = new HashMap<String, List<String>>();
        brand_Id = getIntent().getIntExtra("brand_Id", 0);
        System.out.println(brand_Id);
        brand_Title = getIntent().getStringExtra("brand_Title");
        tvCarName.setText(brand_Title);

        getchosenCar();

    }

    private void init() {

        subBrandCarArr = new ArrayList<String>();

        subBrandArr = new ArrayList<String>();



        iv_backHome = (ImageView) findViewById(R.id.IV_officialAgent);
        tvCarName = (TextView) findViewById(R.id.carName);

        String CurrentLang = Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
        }
    }

    @OnClick(R.id.IV_backHome)
    public void goHome() {
        finish();
    }

    //    ************************************************************************************************************//
    public void getchosenCar() {

        String url = "http://mobileaders.com/saarha/public/api/subBrands";
        JSONObject request = new JSONObject();
        try {
            request.put("brandId", brand_Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest subBrandObject = new JsonObjectRequest(Request.Method.POST, url, request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                subBrandCarArr = new ArrayList<String>();

                                JSONObject subBrand = jsonArray.getJSONObject(i);


                                bTitle = subBrand.getString("subBrandTitle");
                                bId = subBrand.getInt("subBrandId");

                                JSONArray jsonarrayCar = subBrand.getJSONArray("get_cars");
                                subBrandArr.add(i, bTitle);

                                for (int j = 0; j < jsonarrayCar.length(); j++) {
                                    JSONObject subBrandCar = jsonarrayCar.getJSONObject(j);
                                    sub = subBrandCar.getString("carTitle");
                                    subBrandCarArr.add(sub);
                                }
                                expandableListDetail.put("" + subBrandArr.get(i), subBrandCarArr);
                                }

                            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                            adapter = new ExpandableListViewAdapter(expandableListTitle, expandableListDetail, CategoryActivity.this);
                            expandableListView.setAdapter(adapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");

            }
        });


        requestQueue.add(subBrandObject);


    }


}
