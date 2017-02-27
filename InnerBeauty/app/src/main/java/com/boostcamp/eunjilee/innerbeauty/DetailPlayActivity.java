package com.boostcamp.eunjilee.innerbeauty;

import static android.view.View.GONE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.fragment.NaverMapFragment;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.bumptech.glide.Glide;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPlayActivity extends AppCompatActivity {
    @BindView(R.id.app_bar)
    protected AppBarLayout mAppBarLayout;
    @BindView(R.id.fab)
    protected FloatingActionButton mFab;
    @BindView(R.id.fab_naver)
    protected FloatingActionButton mNaverFab;
    @BindView(R.id.fab_naver_band)
    protected FloatingActionButton mNaverBandFab;
    @BindView(R.id.fab_kakao)
    protected FloatingActionButton mKakaoFab;
    @BindView(R.id.fab_facebook)
    protected FloatingActionButton mFaceBookFab;
    @BindView(R.id.imgv_content_detail_title)
    protected ImageView mDetailTitleImageView;
    @BindView(R.id.tv_start_end_date_value)
    protected TextView mStartEndDateTextView;
    @BindView(R.id.tv_open_time_value)
    protected TextView mOpenTimeTextView;
    @BindView(R.id.tv_runtime_value)
    protected TextView mRunTimeTextView;
    @BindView(R.id.tv_place_value)
    protected TextView mPlaceTextView;
    @BindView(R.id.tv_address_value)
    protected TextView mAddressTextView;
    @BindView(R.id.tv_price_value)
    protected TextView mPriceTextView;
    @BindView(R.id.tv_call_value)
    protected TextView mCallTextView;
    @BindView(R.id.tv_ticket_site1_label)
    protected TextView mTicketSite1Label;
    @BindView(R.id.tv_ticket_site2_label)
    protected TextView mTicketSite2Label;
    @BindView(R.id.imgv_ticket_site2)
    protected ImageView mTicketSite2ImageView;
    @BindView(R.id.tv_ticket_site1_value)
    protected TextView mTicketSiteTextView1;
    @BindView(R.id.tv_ticket_site2_value)
    protected TextView mTicketSiteTextView2;
    @BindView(R.id.tv_detail_info_value)
    protected TextView mDetailInfoTextView;
    @BindView(R.id.toolbar_detail)
    protected Toolbar mToolbar;

    private static final String APP_WEB_SITE = "https://eunjilee0430.github.io/GoodDonation/";
    private boolean mFabStatus = false;
    private Animation mFabShowAnimation;
    private Animation mFabHideAnimation;
    private PlayModel mPlay;
    private boolean mShowMapFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_play);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPlay = (PlayModel)intent.getSerializableExtra("Play");
        mToolbar.setTitle(mPlay.getPlayTitle());
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mAppBarLayout.setExpanded(false);
        initDetailPlayInfo();
        initFabAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDetailPlayInfo() {
        Glide.with(this).load(mPlay.getPlayPicture()).into(mDetailTitleImageView);
        if(mPlay.getEndDate().equals("70.01.01")){
            mStartEndDateTextView.setText(mPlay.getStartDate() + " ~ " + "오픈런");
        }else{
            mStartEndDateTextView.setText(mPlay.getStartDate() + " ~ " + mPlay.getEndDate());
        }
        mOpenTimeTextView.setText(mPlay.getPlayTime());
        mRunTimeTextView.setText(mPlay.getPlayRunTime());
        mPlaceTextView.setText(mPlay.getPlayPlace());
        mAddressTextView.setText(mPlay.getPlayAddress());
        mPriceTextView.setText(mPlay.getPricePlay());
        mCallTextView.setText(mPlay.getPlayCall());
        if(mPlay.getPlayTicketSite2()==null){
            mTicketSite1Label.setText("예매 사이트");
            mTicketSiteTextView1.setText(mPlay.getPlayTicketSite1());
            mTicketSite2Label.setVisibility(GONE);
            mTicketSiteTextView2.setVisibility(GONE);
            mTicketSite2ImageView.setVisibility(GONE);
        } else{
            mTicketSiteTextView1.setText(mPlay.getPlayTicketSite1());
            mTicketSiteTextView2.setText(mPlay.getPlayTicketSite2());
        }
        mDetailInfoTextView.setText(mPlay.getPlayDetailInfo());
    }

    private void initFabAnimation() {
        mFabShowAnimation = AnimationUtils.loadAnimation(getApplication(), R.anim.share_fab_show);
        mFabHideAnimation = AnimationUtils.loadAnimation(getApplication(), R.anim.share_fab_hide);
    }

    @OnClick(R.id.fab)
    public void showShareButtons(View view) {
        if (!mFabStatus) { //show
            expandFAB();
            mFabStatus = true;
        } else {
            hideFAB();
            mFabStatus = false;
        }
    }

    @OnClick(R.id.tv_real_map)
    public void showRealMapAction() {
        if(!mShowMapFlag) {
            mShowMapFlag = true;
            Bundle bundle = new Bundle();
            bundle.putString("address", mPlay.getPlayAddress());
            NaverMapFragment naverMapFragment = new NaverMapFragment();
            naverMapFragment.setArguments(bundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.ll_naver_map, naverMapFragment);
            fragmentTransaction.commit();
        }
    }

    @OnClick(R.id.tv_real_call)
    public void checkPermissionForRealCall() {
        new TedPermission(this)
                .setPermissionListener(permissionlistenerCall)
                .setDeniedMessage(getString(R.string.permission_call_deny_ment))
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }

    private void realCallAction() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mPlay.getPlayCall()));
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab_naver)
    public void shareNaver(View view) {
        try {
            String encodedURL = URLEncoder.encode(mPlay.getPlayTicketSite1(), "utf-8");
            String encodedTitle = URLEncoder.encode("[ " + mPlay.getPlayTitle() + " ] _InnerBeauty","utf-8");
            Uri uri = Uri.parse("http://share.naver.com/web/shareView.nhn?url=" + encodedURL
                    + "&title=" + encodedTitle );
            Intent naverIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(naverIntent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab_naver_band)
    public void shareNaverBand(View view) {
        PackageManager packageManager = getBaseContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.nhn.android.band");
        if(intent == null){
            intent = new Intent(intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.band"));
            startActivity(intent);
        } else{
            try {
                String serviceDomain = APP_WEB_SITE; //앱 도메인 ( 임시:앱 소개 사이트)

                StringBuilder sb = new StringBuilder();
                sb.append("[ " ).append(mPlay.getPlayTitle()).append(" ]\n")
                        .append("시간: ").append(mPlay.getPlayTime()).append("\n")
                        .append("런타인: ").append(mPlay.getPlayRunTime()).append("\n")
                        .append("장소: ").append(mPlay.getPlayPlace())
                        .append("주소 ").append(mPlay.getPlayAddress());
                String encodedText = URLEncoder.encode(sb.toString(), "utf-8");
                Uri uri = Uri.parse("bandapp://create/post?text=" + encodedText
                        + "&route=" + serviceDomain);
                Intent bandIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(bandIntent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
    @OnClick(R.id.fab_kakao)
    void shareKakao(View view) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[ " ).append(mPlay.getPlayTitle()).append(" ]\n")
                    .append("시간: ").append(mPlay.getPlayTime()).append("\n")
                    .append("런타인: ").append(mPlay.getPlayRunTime()).append("\n")
                    .append("장소: ").append(mPlay.getPlayPlace())
                    .append("주소 ").append(mPlay.getPlayAddress());
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            final KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
            kakaoTalkLinkMessageBuilder.addText(sb.toString());
            kakaoTalkLinkMessageBuilder.addImage(mPlay.getPlayPicture(), 100, 150);
            //kakaoTalkLinkMessageBuilder.addWebButton(APP_WEB_SITE);
            //kakaoTalkLinkMessageBuilder.addAppButton("")
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab_facebook)
    void shareFacebook() { // all must be not null
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setContentTitle(mPlay.getPlayTitle())
                .setImageUrl(Uri.parse(mPlay.getPlayPicture()))
                .setContentDescription(mPlay.getPlayDetailInfo())
                .setContentUrl(Uri.parse(mPlay.getPlayTicketSite1()))
                .build();
        ShareDialog.show(this, shareLinkContent);
    }

    private void expandFAB() {
        mNaverFab.startAnimation(mFabShowAnimation);
        mNaverFab.setClickable(true);
        mNaverBandFab.startAnimation(mFabShowAnimation);
        mNaverBandFab.setClickable(true);
        mKakaoFab.startAnimation(mFabShowAnimation);
        mKakaoFab.setClickable(true);
        mFaceBookFab.startAnimation(mFabShowAnimation);
        mFaceBookFab.setClickable(true);
    }


    private void hideFAB() {
        mNaverFab.startAnimation(mFabHideAnimation);
        mNaverFab.setClickable(false);
        mNaverBandFab.startAnimation(mFabHideAnimation);
        mNaverBandFab.setClickable(false);
        mKakaoFab.startAnimation(mFabHideAnimation);
        mKakaoFab.setClickable(false);
        mFaceBookFab.startAnimation(mFabHideAnimation);
        mFaceBookFab.setClickable(false);

    }

    PermissionListener permissionlistenerCall = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (ActivityCompat.checkSelfPermission(DetailPlayActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                realCallAction();
            } else {
                Snackbar.make(mCallTextView, "Phone call Permission Denied\n", Snackbar.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Snackbar.make(mCallTextView, "Permission Denied\n" + deniedPermissions.toString(), Snackbar.LENGTH_SHORT).show();
        }
    };

}
