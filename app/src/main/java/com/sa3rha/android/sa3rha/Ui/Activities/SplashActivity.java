package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.BTN_english)
    public void makeEnglish(){
        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        startActivity(new Intent(SplashActivity.this,VideoActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        finish();
    }
    @OnClick(R.id.BTN_arabic)
    public void makeArabic(){
        String languageToLoad  = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        startActivity(new Intent(SplashActivity.this,VideoActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        finish();
    }


}
