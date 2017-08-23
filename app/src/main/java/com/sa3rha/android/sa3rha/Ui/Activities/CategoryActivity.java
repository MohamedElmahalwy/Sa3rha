package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.Controller.ExpandableListViewAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

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
    ActionBarDrawerToggle toggle;
    @BindView(R.id.expandable)
    ExpandableListView expandableListView ;
    private ArrayList<String> expandableListTitle;
    int brand_Id;
    String brand_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);
        init();
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        brand_Id = getIntent().getIntExtra("brand_Id",0);
        brand_Title = getIntent().getStringExtra("brand_Title");

        List<String> fgcroz = new ArrayList<String>();
        fgcroz.add("Brazil");
        fgcroz.add("Spain");
        fgcroz.add("Germany");

        List<String> toyota = new ArrayList<String>();
        toyota.add("تويوتا كورلا هاي لاين");
        toyota.add("تويوتا كورلا بايز لاين");



        expandableListDetail.put("تويوتا اف جاي كروزر", fgcroz);
        expandableListDetail.put("تويوتا كورولا",toyota);

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(expandableListTitle,expandableListDetail,CategoryActivity.this);
        expandableListView.setAdapter(adapter);


    }

    private void init() {
        String CurrentLang= Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
        }
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                startActivity(new Intent(CategoryActivity.this,NewCarDetailsActivity.class));
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
                return false;
            }
        });
    }
    @OnClick(R.id.IV_backHome)
    public void goHome(){
        finish();
    }


}
