package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.Controller.MainPagerAdapter;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;
import com.sa3rha.android.sa3rha.Ui.Fragments.NewBrandsFragment;
import com.sa3rha.android.sa3rha.Ui.Fragments.OldCarsFragment;
import com.sa3rha.android.sa3rha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.IV_search)
    ImageView iv_search;
    @BindView(R.id.TV_searchForCar)
    TextView tv_searchForCar;
    @BindView(R.id.Linear_ContainerOfSearchBox)
    LinearLayout linear_containerOfSearchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        init();
    }

    private void init() {

    }

    private void setupViewPager(ViewPager viewPager) {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OldCarsFragment(), getString(R.string.oldCar));
        adapter.addFrag(new NewBrandsFragment(), getString(R.string.newCar));
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.Linear_ContainerOfSearchBox)
    public void doSearch() {
        startActivity(new Intent(this,serachActivity.class));
        overridePendingTransition(R.anim.slide_down,R.anim.slide_up);
    }

    @OnClick(R.id.TV_searchForCar)
    public void doSearch2(){
        startActivity(new Intent(this,serachActivity.class));
        overridePendingTransition(R.anim.slide_down,R.anim.slide_up);

    }


}
