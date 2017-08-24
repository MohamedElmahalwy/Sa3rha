package com.sa3rha.android.sa3rha.Ui.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.sa3rha.android.sa3rha.Controller.ComparePagerAdapter;
import com.sa3rha.android.sa3rha.R;
import com.sa3rha.android.sa3rha.Ui.BaseActivity;
import com.sa3rha.android.sa3rha.Ui.Fragments.Compare_FristFragment;
import com.sa3rha.android.sa3rha.Ui.Fragments.Compare_SecondFragment;

import java.util.Locale;

public class CompareActivity extends BaseActivity implements View.OnClickListener{

    TabLayout tabLayout ;
    ViewPager viewPager;
    ImageView iv_backHome;
////    YouTubePlayerView youTubePlayerView;
////    ImageView iv_playVideo;
//    RelativeLayout rl_VideoContainer;
    ComparePagerAdapter pagerAdapter;
//    static final String Api_Key="AIzaSyBllWxxH8RoWCElkiTBP1XZOQZ8hdEY3As";
//    static final String VedioID="1N9KveJ-FU8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        init();
    }
    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewpager();
        tabLayout.setupWithViewPager(viewPager);
        setupTablayout();
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.lightBllue));
        iv_backHome= (ImageView) findViewById(R.id.IV_backHome);
        iv_backHome.setOnClickListener(this);
        String CurrentLang= Locale.getDefault().getLanguage();
        if (CurrentLang == "ar") {
            iv_backHome.setImageResource(R.drawable.ic_arrow_forward_white_24dp);

        }
//        youTubePlayerView= (YouTubePlayerView) findViewById(R.id.YouTubeView);
//        iv_playVideo=(ImageView)findViewById(R.id.IV_playVideo);
//        iv_playVideo.setOnClickListener(this);
//        rl_VideoContainer= (RelativeLayout) findViewById(R.id.RL_VideoContainer);
    }
    private void setupTablayout() {

       tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
      tabLayout.getTabAt(1).setIcon(R.mipmap.ic_launcher);
    }

    private void setupViewpager() {
        pagerAdapter = new ComparePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new Compare_FristFragment(),"Mercedes_Benz C180");
        pagerAdapter.addFragment(new Compare_SecondFragment(),"Mercedes_Benz C200");
        viewPager.setAdapter(pagerAdapter);

    }
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
//
//        if(!b){
//            youTubePlayer.cueVideo(VedioID);
//        }
//        youTubePlayer.setShowFullscreenButton(false);
//        YouTubePlayer.PlayerStateChangeListener playerStateChangeListener= new YouTubePlayer.PlayerStateChangeListener() {
//            @Override
//            public void onLoading() {
//
//            }
//
//            @Override
//            public void onLoaded(String s) {
//                youTubePlayer.play();
//
//            }
//
//            @Override
//            public void onAdStarted() {
//                youTubePlayer.next();
//            }
//
//            @Override
//            public void onVideoStarted() {
//
//            }
//
//            @Override
//            public void onVideoEnded() {
//
//            }
//
//            @Override
//            public void onError(YouTubePlayer.ErrorReason errorReason) {
//
//            }
//        };
//        YouTubePlayer.PlaybackEventListener playbackEventListener=new YouTubePlayer.PlaybackEventListener() {
//            @Override
//            public void onPlaying() {
//
//            }
//
//            @Override
//            public void onPaused() {
//
//            }
//
//            @Override
//            public void onStopped() {
//
//            }
//
//            @Override
//            public void onBuffering(boolean b) {
//
//            }
//
//            @Override
//            public void onSeekTo(int i) {
//
//            }
//        };
//        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
//        youTubePlayer.setPlaybackEventListener(playbackEventListener);
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//    }
//


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.IV_backHome){
            finish();
        }
//        else if(id==R.id.IV_playVideo){
//            rl_VideoContainer.setVisibility(View.GONE);
//            youTubePlayerView.initialize(Api_Key,this);
//        }
    }
}
