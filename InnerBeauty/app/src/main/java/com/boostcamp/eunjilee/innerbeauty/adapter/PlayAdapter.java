package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.boostcamp.eunjilee.innerbeauty.DetailPlayActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.UserSharedPreference;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.module.ContentsModule;
import com.boostcamp.eunjilee.innerbeauty.module.PlayLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.PlayViewHolder> {
    private static final int PLAY_TYPE=2;
    private final Context mContext;
    private final List<PlayModel> mPlayList;
    private final ContentsModule mContentsModule;
    private final PlayLoadModule mPlayModule;
    private UserSharedPreference mUserSharedPreference;

    public PlayAdapter(Context context, List<PlayModel> playModels) {
        mContext = context;
        mPlayList = playModels;
        mContentsModule = new ContentsModule();
        mPlayModule = new PlayLoadModule();
        mUserSharedPreference = new UserSharedPreference(mContext);
    }

    @Override
    public PlayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.play_list_item, parent, false);
        return new PlayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayViewHolder holder, int position) {
        PlayModel play = mPlayList.get(position);
        holder.setPlay(play);
    }

    @Override
    public int getItemCount() {
        if (mPlayList == null)
            return 0;
        else
            return mPlayList.size();
    }

    class PlayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_list_play)
        protected ImageView mPlayImageView;
        @BindView(R.id.tv_play_date)
        protected TextView mPlaytDateTextView;
        @BindView(R.id.tv_play_place)
        protected TextView mPlayPlaceTextView;
        @BindView(R.id.btn_like_play)
        protected ToggleButton mLikeBtn;

        private PlayModel mPlay;

        public PlayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setDate(String startDate, String endDate) {
            mPlaytDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mPlayPlaceTextView.setText(place);
        }

        public void setPlay(PlayModel play) {
            mPlay = play;
            Glide.with(mContext).load(play.getPlayPicture())
                    .thumbnail(0.1f)
                    .into(mPlayImageView);
            setDate(play.getStartDate(), play.getEndDate());
            setPlace(play.getPlayPlace());
            mLikeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mContentsModule.registerFavoriteContents(mUserSharedPreference.getUserId(), mPlay.getPlayId(), PLAY_TYPE, new ContentsService.registerFavoriteContentsCallback(){
                            @Override
                            public void success() {
                                Snackbar.make(mPlayImageView, R.string.snb_add_favorite_play_success, Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void error(Throwable throwable) {
                                Snackbar.make(mPlayImageView, R.string.snb_add_favorite_play_fail, Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        mContentsModule.deleteFavoriteContents(mUserSharedPreference.getUserId(), mPlay.getPlayId(), PLAY_TYPE, new ContentsService.deleteFavoriteContentsCallback(){
                            @Override
                            public void success() {
                                Toast.makeText(mContext, "like->unlike", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void error(Throwable throwable) {

                            }
                        });
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            mPlayModule.addClickNumToPlay(mPlay.getPlayId(), new PlayService.addClickNumCallback() {
                @Override
                public void success() {
                    Log.v("daisy", "add success");
                }
                @Override
                public void error(Throwable throwable) {

                }
            });
            Intent startDetailActivity = new Intent(mContext, DetailPlayActivity.class);
            startDetailActivity.putExtra("Play", mPlay);
            mContext.startActivity(startDetailActivity);
        }
    }
}
