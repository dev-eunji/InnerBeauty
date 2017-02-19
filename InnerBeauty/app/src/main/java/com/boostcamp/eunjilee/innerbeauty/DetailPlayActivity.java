package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPlayActivity extends AppCompatActivity {

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

    private PlayModel mPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPlay = (PlayModel) intent.getSerializableExtra("Play");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        toolbar.setTitle(mPlay.getPlayTitle());
        setSupportActionBar(toolbar);
        initDetailPlayInfo();
    }

<<<<<<< HEAD
        initDetailPlayInfo();

    }

=======
>>>>>>> shareFunction
    private void initDetailPlayInfo() {
        Glide.with(this).load(mPlay.getPlayPicture()).into(mDetailTitleImageView);
        mStartEndDateTextView.setText(mPlay.getStartDate() + " ~ " + mPlay.getEndDate());
        mOpenTimeTextView.setText(mPlay.getOpenTime() + " ~ " + mPlay.getCloseTime());
        mCloseDateTextView.setText(mPlay.getCloseDate());
        mPlaceTextView.setText(mPlay.getPlayPlace());
        mPriceTextView.setText("어른 : " + mPlay.getPriceAdult() + " 어린이 : " + mPlay.getPriceChildren());
        mCallTextView.setText(mPlay.getPlayCall());

    }

    @OnClick(R.id.fab)
    public void showShareButtons(View view) {

    }


}
