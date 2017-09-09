package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_used_car_activity extends BaseActivity {
    private static final int PICK_GALLARY = 1;

    private Uri uri;
    int num_of_image = 0;
    List<Uri> uriList;
    @BindView(R.id.IV_backHome)
    ImageView iv_backHome;
    @BindView(R.id.BTN_uploadbtn)
    Button btn_upload;
    @BindView(R.id.IV_CarImages)
    ImageView IV_carImages;
    @BindView(R.id.text_num_of_image)
    TextView tx_num_of_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_used_car);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        uriList = new ArrayList<>();
        //init();
    }

    private void init() {
        String CurrentLang = Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
        }
    }

    public void onupload(View view) {
        if (((4 - num_of_image) <= 0) == false) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_GALLARY);
        } else {
            Toast.makeText(Add_used_car_activity.this, "cant upload image again ", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_GALLARY && resultCode == RESULT_OK && uriList.size() < 4) {
            // Toast.makeText(MainActivity.this,"Main toast",Toast.LENGTH_SHORT).show();
            uri = data.getData();
            //IV_carImages.setImageURI(uri);
            uriList.add(uri);
            num_of_image++;
            Toast.makeText(Add_used_car_activity.this, "only " + (4 - num_of_image) + " image ", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.IV_backHome)
    public void goBack() {
        finish();
    }

}
