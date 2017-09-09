package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.Controller.MainPagerAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;
import com.sa3rha.android.sa3rha.Ui.Fragments.NewBrandsFragment;
import com.sa3rha.android.sa3rha.Ui.Fragments.OldCarsFragment;

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
    }

    private void setupViewPager(ViewPager viewPager) {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OldCarsFragment(), getString(R.string.oldCar));
        adapter.addFrag(new NewBrandsFragment(), getString(R.string.newCar));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        //Set NewBrandsFragment the main selected item in the start
        TabLayout.Tab newBrandsTab = tabLayout.getTabAt(1);
        newBrandsTab.select();
        viewPager.setCurrentItem(1);
        changeTabsFont();
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

    /**
     * This method is to change tabs indicators text font
     */
    void changeTabsFont() {
        ViewGroup tabsViewGroup = (ViewGroup) tabLayout.getChildAt(0);
        byte tabsCount = (byte) tabsViewGroup.getChildCount();
        for (byte j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) tabsViewGroup.getChildAt(j);
            int tabChildrenCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildrenCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Cairo-Bold.ttf"));
                }
            }
        }
    }
}
