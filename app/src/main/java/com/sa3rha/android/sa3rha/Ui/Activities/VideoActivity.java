package com.sa3rha.android.sa3rha.Ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sa3rha.android.sa3rha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static final String Api_Key="AIzaSyBllWxxH8RoWCElkiTBP1XZOQZ8hdEY3As";
    static final String VedioID="1N9KveJ-FU8";
    @BindView(R.id.YouTubeView)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.RL_VideoContainer)
    RelativeLayout rl_VideoContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        applyAppFont();
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
                startActivity(new Intent(VideoActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
                finish();
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
    @OnClick(R.id.IV_playVideo)
    public void playVideo(){
        rl_VideoContainer.setVisibility(View.GONE);
        youTubePlayerView.initialize(Api_Key,this);
    }
    @OnClick(R.id.BTN_skip)
    public void skip(){
        startActivity(new Intent(VideoActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        finish();
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
}
