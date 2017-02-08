package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailContentActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.fab_naver)
    FloatingActionButton mNaverFab;
    @BindView(R.id.fab_kakao)
    FloatingActionButton mKakaoFab;
    @BindView(R.id.fab_facebook)
    FloatingActionButton mFaceBookFab;
    @BindView(R.id.imgv_content_detail_title)
    ImageView mDetailTitleImageView;
    @BindView(R.id.tv_detail)
    TextView mDetailTextView;

    private boolean FAB_Status = false;
    private ExhibitionModel mExhibition;
    //Animations
    Animation show_fab_naver;
    Animation hide_fab_naver;
    Animation show_fab_kakao;
    Animation hide_fab_kakao;
    Animation show_fab_facebook;
    Animation hide_fab_facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        toolbar.setTitle("Title");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mExhibition = (ExhibitionModel) intent.getSerializableExtra("Exhibition");

        //testCode
        Glide.with(this).load(mExhibition.getExhibitionPicture()).into(mDetailTitleImageView);
        mDetailTextView.setText(mExhibition.getStartDate());

        //Animations
        show_fab_naver = AnimationUtils.loadAnimation(getApplication(), R.anim.naver_share_fab_show);
        hide_fab_naver = AnimationUtils.loadAnimation(getApplication(), R.anim.naver_share_fab_hide);
        show_fab_kakao = AnimationUtils.loadAnimation(getApplication(), R.anim.kakao_share_fab_show);
        hide_fab_kakao = AnimationUtils.loadAnimation(getApplication(), R.anim.kakao_share_fab_hide);
        show_fab_facebook = AnimationUtils.loadAnimation(getApplication(), R.anim.facebook_share_fab_show);
        hide_fab_facebook = AnimationUtils.loadAnimation(getApplication(), R.anim.facebook_share_fab_hide);

    }

    @OnClick(R.id.fab)
    public void showShareButtons(View view) {
        if (FAB_Status == false) { //show
            expandFAB();
            FAB_Status = true;
        } else {
            hideFAB();
            FAB_Status = false;
        }
    }

    @OnClick(R.id.fab_naver)
    public void shareNaver(View view) {
        Toast.makeText(getApplication(), "NAVER", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_kakao)
    public void shareKakao(View view) {
        Toast.makeText(getApplication(), "KAKAO", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_facebook)
    public void shareFacebook(View view) {
        Toast.makeText(getApplication(), "FACEBOOK", Toast.LENGTH_SHORT).show();
    }

    //TODO : 애니메이션
    private void expandFAB() {
        //Floating Action Button Naver
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mNaverFab.getLayoutParams();
//        layoutParams.rightMargin += (int) (mNaverFab.getWidth() * 1.7);
//        layoutParams.bottomMargin += (int) (mNaverFab.getHeight() * 0.25);
//        mNaverFab.setLayoutParams(layoutParams);
        mNaverFab.startAnimation(show_fab_naver);
        mNaverFab.setClickable(true);

        //Floating Action Button Kakao
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) mKakaoFab.getLayoutParams();
//        layoutParams2.rightMargin += (int) (mKakaoFab.getWidth() * 1.5);
//        layoutParams2.bottomMargin += (int) (mKakaoFab.getHeight() * 1.5);
//        mKakaoFab.setLayoutParams(layoutParams2);
        mKakaoFab.startAnimation(show_fab_kakao);
        mKakaoFab.setClickable(true);

        //Floating Action Button Facebook
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) mFaceBookFab.getLayoutParams();
//        layoutParams3.rightMargin += (int) (mFaceBookFab.getWidth() * 0.25);
//        layoutParams3.bottomMargin += (int) (mFaceBookFab.getHeight() * 1.7);
//        mFaceBookFab.setLayoutParams(layoutParams3);
        mFaceBookFab.startAnimation(show_fab_facebook);
        mFaceBookFab.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button Naver
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mNaverFab.getLayoutParams();
//        layoutParams.rightMargin -= (int) (mNaverFab.getWidth() * 1.7);
//        layoutParams.bottomMargin -= (int) (mNaverFab.getHeight() * 0.25);
//        mNaverFab.setLayoutParams(layoutParams);
        mNaverFab.startAnimation(hide_fab_naver);
        mNaverFab.setClickable(false);

        //Floating Action Button Kakao
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) mKakaoFab.getLayoutParams();
//        layoutParams2.rightMargin -= (int) (mKakaoFab.getWidth() * 1.5);
//        layoutParams2.bottomMargin -= (int) (mKakaoFab.getHeight() * 1.5);
//        mKakaoFab.setLayoutParams(layoutParams2);
        mKakaoFab.startAnimation(hide_fab_kakao);
        mKakaoFab.setClickable(false);

        //Floating Action Button Facebook
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) mFaceBookFab.getLayoutParams();
//        layoutParams3.rightMargin -= (int) (mFaceBookFab.getWidth() * 0.25);
//        layoutParams3.bottomMargin -= (int) (mFaceBookFab.getHeight() * 1.7);
//        mFaceBookFab.setLayoutParams(layoutParams3);
        mFaceBookFab.startAnimation(hide_fab_facebook);
        mFaceBookFab.setClickable(false);
    }

}
