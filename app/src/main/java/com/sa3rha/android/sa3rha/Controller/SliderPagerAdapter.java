package com.sa3rha.android.sa3rha.Controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.R;
import com.squareup.picasso.Picasso;

public class SliderPagerAdapter extends PagerAdapter {
    Context context;
  //  ArrayList<String> image_arraylist;
    LayoutInflater layoutInflater;

    // public SliderPagerAdapter(Context  context, ArrayList<String> image_arraylist)
    public SliderPagerAdapter(Context  context) {
        this. context =  context;
       // this.image_arraylist = image_arraylist;
        layoutInflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
        Picasso.with(context.getApplicationContext())
                .load(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)         // optional
                .into(im_slider);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        //return image_arraylist.size();
        return 4;
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

