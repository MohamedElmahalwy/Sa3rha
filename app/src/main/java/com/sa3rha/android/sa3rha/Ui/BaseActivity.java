package com.sa3rha.android.sa3rha.Ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sa3rha.android.sa3rha.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * This BaseActivity class extends {@link AppCompatActivity} and other activities in the app extends it
 * instead of direct extends of {@link AppCompatActivity}
 * to apply custom fonts and themes for all app activities.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyAppFont();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * This method to apply custom font in activity using third-party library.
     */
    private void applyAppFont(){
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Cairo-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
    }
}
