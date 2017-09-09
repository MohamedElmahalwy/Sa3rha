package com.sa3rha.android.sa3rha.Ui.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sa3rha.android.sa3rha.Controller.SliderPagerAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;
import com.sa3rha.android.sa3rha.Utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OldCarDetailsActivity extends BaseActivity {

    @BindView(R.id.IV_backHome)
    ImageView iv_backHome;
    @BindView(R.id.IV_addToCompare)
    ImageView iv_addToCompare;
    @BindView(R.id.vp_slider)
    ViewPager vp_slider;
    @BindView(R.id.LL_dots)
    LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    Dialog dialog;
    //page position  belong to viewpager ImageView
    int page_position = 0;
    private TextView[] dots;
    TextView tvUsedCarDetailsPrice, tvUsedCarDetailsDescription, tvUsedCarDetailsName, tvUsedCarDetailsNameToolBar,
            tvUsedCarDetailsNameTable, tvUsedCarDetailsPriceTable, tvUsedCarDetailsModel, tvUsedCarDetailsYear;
    ArrayList<String> usedCarDetailsImages;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_car_details);
        requestQueue = Volley.newRequestQueue(this);
        ButterKnife.bind(this);
        init();
        addBottomDots(0);
        getUsedCarDetails();
    }

    private void init() {
//        String CurrentLang = Locale.getDefault().getLanguage();
//        if (CurrentLang == "ar") {
//            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
//        }
        usedCarDetailsImages = new ArrayList<String>();
        // viewpager ImageView
        sliderPagerAdapter = new SliderPagerAdapter(this, usedCarDetailsImages);
        vp_slider.setAdapter(sliderPagerAdapter);
        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tvUsedCarDetailsPrice = (TextView) findViewById(R.id.tvUsedCarDetailsPrice);
        tvUsedCarDetailsDescription = (TextView) findViewById(R.id.tvUsedCarDetailsDescription);
        tvUsedCarDetailsName = (TextView) findViewById(R.id.tvUsedCarDetailsName);
        tvUsedCarDetailsNameToolBar = (TextView) findViewById(R.id.tvUsedCarDetailsNameToolBar);
        tvUsedCarDetailsNameTable = (TextView) findViewById(R.id.tvUsedCarDetailsNameTable);
        tvUsedCarDetailsPriceTable = (TextView) findViewById(R.id.tvUsedCarDetailsPriceTable);
        tvUsedCarDetailsModel = (TextView) findViewById(R.id.tvUsedCarDetailsModel);
        tvUsedCarDetailsYear = (TextView) findViewById(R.id.tvUsedCarDetailsYear);
    }

    //dots of viewpager ImageView
    private void addBottomDots(int currentPage) {
        // dots = new TextView[slider_image_list.size()];
        dots = new TextView[usedCarDetailsImages.size()];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#9e9e9e"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
    }

    @OnClick(R.id.IV_backHome)
    public void backHome() {
        finish();
    }

    @OnClick(R.id.IV_addToCompare)
    public void addToCompar() {
        dialog = new Dialog(this);
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


    //*****************************************************************************************************************************************
    public void getUsedCarDetails() {
        String url = Constants.API_LINK + "usedCars/getCarDetails";
        JSONObject request = new JSONObject();
        try {
            request.put("carId", Integer.parseInt(getIntent().getStringExtra("carId")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseResponseJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "error");
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void parseResponseJson(JSONObject response) {
//        ProgressDialog pd = ProgressDialog.show(getApplicationContext(),null,"Please wait");
//        if(pd!=null && pd.isShowing())
//            pd.dismiss();
        try {
            JSONObject jsonObject = response.getJSONObject("data");
            String usedCarPrice = jsonObject.getString("usedCarPrice");
            tvUsedCarDetailsPrice.setText(usedCarPrice);
            tvUsedCarDetailsPriceTable.setText(usedCarPrice);

            String usedCarDescription = jsonObject.getString("usedCarDescription");
            tvUsedCarDetailsDescription.setText(usedCarDescription);

            String usedCarModel = jsonObject.getString("usedCarModel");
            tvUsedCarDetailsModel.setText(usedCarModel);

            String usedCarYear = jsonObject.getString("usedCarYear");
            tvUsedCarDetailsYear.setText(usedCarYear);

            JSONArray images = jsonObject.getJSONArray("images");

            for (int i = 0; i < images.length(); i++) {
                JSONObject usedCarImages = images.getJSONObject(i);
                String imageTitle = usedCarImages.getString("imageTitle");
                usedCarDetailsImages.add(imageTitle);
            }

            JSONObject sub_brand = jsonObject.getJSONObject("sub_brand");
            String subBrandTitle = sub_brand.getString("subBrandTitle");
            tvUsedCarDetailsName.setText(subBrandTitle);
            tvUsedCarDetailsNameToolBar.setText(subBrandTitle);
            tvUsedCarDetailsNameTable.setText(subBrandTitle);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        sliderPagerAdapter.notifyDataSetChanged();
    }

}
