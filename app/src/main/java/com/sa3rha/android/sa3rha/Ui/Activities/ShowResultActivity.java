package com.sa3rha.android.sa3rha.Ui.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.Controller.ShowResultAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowResultActivity extends BaseActivity {

    @BindView(R.id.RV_ShowResult)
    RecyclerView rv_ShowResult;
    @BindView(R.id.IV_backHome)
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        rv_ShowResult.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ShowResultAdapter adapter=new ShowResultAdapter(this);
        rv_ShowResult.setAdapter(adapter);
        String CurrentLang= Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_back.setImageResource(R.drawable.ic_arrow_forward_white_24dp);

        }
    }
    @OnClick(R.id.IV_backHome)
    public void backHome(){
        finish();
    }


}
