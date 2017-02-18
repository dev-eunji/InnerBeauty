package com.boostcamp.eunjilee.innerbeauty;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.bumptech.glide.Glide;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailExhibitionActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    protected FloatingActionButton mFab;
    @BindView(R.id.fab_naver)
    protected FloatingActionButton mNaverFab;
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
    @BindView(R.id.tv_close_date_value)
    protected TextView mCloseDateTextView;
    @BindView(R.id.tv_place_value)
    protected TextView mPlaceTextView;
    @BindView(R.id.tv_price_value)
    protected TextView mPriceTextView;
    @BindView(R.id.tv_call_value)
    protected TextView mCallTextView;
    @BindView(R.id.toolbar_detail)
    protected Toolbar mToolbar;

    private static final String APP_WEB_SITE = "https://eunjilee0430.github.io/GoodDonation/";
    private boolean mFabStatus = false;
    private Animation mFabShowAnimation;
    private Animation mFabHideAnimation;

    private ExhibitionModel mExhibition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mExhibition = (ExhibitionModel) intent.getSerializableExtra("Exhibition");
        mToolbar.setTitle(mExhibition.getExhibitionTitle());
        setSupportActionBar(mToolbar);
        initDetailExhibitionInfo();
        initFabAnimation();
    }

    private void initDetailExhibitionInfo() {
        Glide.with(this).load(mExhibition.getExhibitionPicture()).into(mDetailTitleImageView);
        mStartEndDateTextView.setText(mExhibition.getStartDate() + " ~ " + mExhibition.getEndDate());
        mOpenTimeTextView.setText(mExhibition.getOpenTime() + " ~ " + mExhibition.getCloseTime());
        mCloseDateTextView.setText(mExhibition.getCloseDate());
        mPlaceTextView.setText(mExhibition.getExhibitionPlace());
        mPlaceTextView.setText(mExhibition.getExhibitionAddress());
        mPriceTextView.setText("어른 : " + mExhibition.getPriceAdult() + " 어린이 : " + mExhibition.getPriceChildren());
        mCallTextView.setText(mExhibition.getExhibitionCall());
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
    public void shoqRealMapAction() {
        //Intent showMap = new Intent(this, MapActivity.class);
        //startActivity(showMap);
    }

    @OnClick(R.id.tv_real_call)
    public void checkPermissionForRealCall() {
        new TedPermission(this)
                .setPermissionListener(permissionlistenerCall)
                .setDeniedMessage("If you reject permission,you can not use setting address service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }

    private void realCallAction() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mExhibition.getExhibitionCall()));
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab_naver)
    public void shareNaver(View view) {
        PackageManager packageManager = getBaseContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.nhn.android.band");
        if(intent == null){
            intent = new Intent(intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.band"));
            startActivity(intent);
        } else{
            try {
                String serviceDomain = APP_WEB_SITE; //앱 도메인 ( 임시:앱 소개 사이트)
                String encodedText = URLEncoder.encode(mExhibition.getExhibitionTitle() + "\n"
                        + mExhibition.getStartDate() + " ~ " + mExhibition.getEndDate()
                        + "\n" + mExhibition.getExhibitionPlace(), "utf-8");
                Uri uri = Uri.parse("bandapp://create/post?text=" + encodedText
                        + "&route=" + serviceDomain);
                Intent bandIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(bandIntent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO : Dialog들끼리 빼내기
    @OnClick(R.id.fab_kakao)
    void shareKakao(View view) {
        try {
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            final KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
            kakaoTalkLinkMessageBuilder.addText(mExhibition.getExhibitionTitle() + "\n"
                    + mExhibition.getStartDate() + " ~ " + mExhibition.getEndDate()
                    + "\n" + mExhibition.getExhibitionPlace());
            kakaoTalkLinkMessageBuilder.addImage(mExhibition.getExhibitionPicture(), 160, 160);
            kakaoTalkLinkMessageBuilder.addWebButton(APP_WEB_SITE);
            //kakaoTalkLinkMessageBuilder.addAppButton("")
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab_facebook)
    void shareFacebook() { // all must be not null
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setContentTitle(mExhibition.getExhibitionTitle())
                //.setContentUrl(Uri.parse(mExhibition.getExhibitionSite()))
                .setImageUrl(Uri.parse(mExhibition.getExhibitionPicture()))
                .setContentDescription(mExhibition.getExhibitionAddress())
                .build();
        ShareDialog.show(this, shareLinkContent);
    }

    //TODO : 애니메이션
    private void expandFAB() {
        mNaverFab.startAnimation(mFabShowAnimation);
        mNaverFab.setClickable(true);
        mKakaoFab.startAnimation(mFabShowAnimation);
        mKakaoFab.setClickable(true);
        mFaceBookFab.startAnimation(mFabShowAnimation);
        mFaceBookFab.setClickable(true);
    }


    private void hideFAB() {
        mNaverFab.startAnimation(mFabHideAnimation);
        mNaverFab.setClickable(false);
        mKakaoFab.startAnimation(mFabHideAnimation);
        mKakaoFab.setClickable(false);
        mFaceBookFab.startAnimation(mFabHideAnimation);
        mFaceBookFab.setClickable(false);

    }

    PermissionListener permissionlistenerCall = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (ActivityCompat.checkSelfPermission(DetailExhibitionActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                realCallAction();
            } else {
                Toast.makeText(DetailExhibitionActivity.this, "Phone call Permission Denied\n", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(DetailExhibitionActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
