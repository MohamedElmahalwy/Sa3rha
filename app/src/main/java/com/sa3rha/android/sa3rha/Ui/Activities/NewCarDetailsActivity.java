package com.sa3rha.android.sa3rha.Ui.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sa3rha.android.sa3rha.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewCarDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener , View.OnClickListener{

    static final String Api_Key="AIzaSyBllWxxH8RoWCElkiTBP1XZOQZ8hdEY3As";
    static final String VedioID="1N9KveJ-FU8";
    Dialog dialog;
    @BindView(R.id.YouTubeView)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.IV_backHome)
    ImageView iv_backHome;
    @BindView(R.id.IV_playVideo)
    ImageView iv_playVideo;
    @BindView(R.id.IV_addToCompare)
    ImageView iv_addToCompare;
    @BindView(R.id.RL_VideoContainer)
    RelativeLayout rl_VideoContainer;
    @BindView(R.id.BTN_insurance_offer)
    Button btn_insurance_offer;
    @BindView(R.id.BTN_installment_offer)
    Button btn_installment_offer;
    @BindView(R.id.BTN_shading_offer)
    Button btn_shading_offer;
    @BindView(R.id. BTN_makeOrder)
    Button btn_makeOrder;
    @BindView(R.id.IV_doCompare)
    ImageView iv_doCompare;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_details);
        ButterKnife.bind(this);
        applyAppFont();
        init();
    }

    private void init() {
        btn_installment_offer.setOnClickListener(this);
        btn_insurance_offer.setOnClickListener(this);
        btn_shading_offer.setOnClickListener(this);
        btn_makeOrder.setOnClickListener(this);
        iv_playVideo.setOnClickListener(this);
        iv_backHome.setOnClickListener(this);
        iv_addToCompare.setOnClickListener(this);
        iv_doCompare.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {

        if(!b){
            youTubePlayer.cueVideo(VedioID);
        }
        youTubePlayer.setShowFullscreenButton(false);
        YouTubePlayer.PlayerStateChangeListener playerStateChangeListener= new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {
                youTubePlayer.play();

            }

            @Override
            public void onAdStarted() {
                youTubePlayer.next();
            }

            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onVideoEnded() {

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };
        YouTubePlayer.PlaybackEventListener playbackEventListener=new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        };
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    }


    /**
     * This method to apply custom font in activity using third-party library.
     */
    void applyAppFont(){
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Cairo-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.BTN_insurance_offer){
            Intent i=new Intent(this, OfferActivity.class);
            //1 mean insurance_offer
            i.putExtra("target_offer",1);
            startActivity(i);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        }else if(id==R.id.BTN_installment_offer){
            Intent i=new Intent(this, OfferActivity.class);
            //2 mean insurance_offer
            i.putExtra("target_offer",2);
            startActivity(i);
           overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        } else if(id==R.id.BTN_shading_offer){
            Intent i=new Intent(this, OfferActivity.class);
            //3 mean insurance_offer
            i.putExtra("target_offer",3);
            startActivity(i);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        }else if(id==R.id.BTN_makeOrder){
            Toast.makeText(this,"order made",Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.IV_playVideo){
            rl_VideoContainer.setVisibility(View.GONE);
            youTubePlayerView.initialize(Api_Key,this);
        }
        else if(id==R.id.IV_backHome){
            finish();
        }
        else if(id==R.id.IV_addToCompare){
            addedToCombare();
        }else if(id==R.id.IV_doCompare){
            startActivity(new Intent(NewCarDetailsActivity.this,CompareActivity.class));
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        }

    }

    public void addedToCombare() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_to_combare);
        //set background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn_continue = (Button) dialog.findViewById(R.id.BTN_continue);
        // to set width and height
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
