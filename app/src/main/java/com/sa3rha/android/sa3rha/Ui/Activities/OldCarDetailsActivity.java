package com.sa3rha.android.sa3rha.Ui.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.Controller.SliderPagerAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_car_details);
        ButterKnife.bind(this);
        init();
        addBottomDots(0);

    }

    private void init() {
        String CurrentLang= Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
        }
       // viewpager ImageView
        sliderPagerAdapter = new SliderPagerAdapter(this);
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
    }
    //dots of viewpager ImageView
    private void addBottomDots(int currentPage) {
       // dots = new TextView[slider_image_list.size()];
          dots=new TextView[4];
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
    public void backHome(){
    finish();
    }
    @OnClick(R.id.IV_addToCompare)
    public void addToCompar(){
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


}
