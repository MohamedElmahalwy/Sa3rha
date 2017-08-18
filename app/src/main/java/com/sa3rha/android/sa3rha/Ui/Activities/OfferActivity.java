package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.Controller.OffersAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sa3rha.android.sa3rha.R.id.TV_offerName;

public class OfferActivity extends BaseActivity {
    @BindView(R.id.RV_offer)
    RecyclerView rv_offer;
    @BindView(R.id.IV_backHome)
    ImageView iv_back;
    @BindView(TV_offerName)
    TextView tv_offerName;
    // 1 insurance_offer ,,, 2 installment_offer ,,, 3Shading Offer
    int  target_offer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent i=getIntent();
        target_offer=i.getIntExtra("target_offer",0);
        //set title of the activity
        if(target_offer==1){
            tv_offerName.setText(R.string.insurance_offer);
        }else if(target_offer==2){
            tv_offerName.setText(R.string.installment_offer);
        }else if(target_offer==3){
            tv_offerName.setText(R.string.shading_offer);
        }
        String CurrentLang= Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_back.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
        }
        //chick wat offer want by target_offer
        //
        rv_offer.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        OffersAdapter adapter=new OffersAdapter(this,target_offer);
        rv_offer.setAdapter(adapter);

    }
    @OnClick(R.id.IV_backHome)
    public void goBack(){
       finish();
    }
}
