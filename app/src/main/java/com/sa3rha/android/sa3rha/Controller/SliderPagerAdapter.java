package com.sa3rha.android.sa3rha.Controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.Models.UsedCar;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> image_arraylist;
    LayoutInflater layoutInflater;

    public SliderPagerAdapter(Context context, ArrayList<String> image_arraylist) {
        this.context = context;
        this.image_arraylist = image_arraylist;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
        Picasso.with(context.getApplicationContext())
                .load(Constants.MEDIA_LINK + Constants.CarsImages + image_arraylist.get(position).toString())
                .into(im_slider);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

