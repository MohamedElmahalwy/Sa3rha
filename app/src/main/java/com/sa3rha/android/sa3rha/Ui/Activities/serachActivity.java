package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.sa3rha.android.sa3rha.Controller.SerachAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class serachActivity extends BaseActivity {

    @BindView(R.id.RV_allBrands)
    RecyclerView rv_allBrands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        rv_allBrands.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SerachAdapter adapter=new SerachAdapter(this);
        rv_allBrands.setAdapter(adapter);
    }
    @OnClick(R.id.BTN_ShowResult)
    public void showResult(){
        startActivity(new Intent(this,ShowResultActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
    }
    @OnClick(R.id.IV_clear)
    public void cancelSerarch(){
        finish();
    }
}
