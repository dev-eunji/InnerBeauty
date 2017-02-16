package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.DetailPlayActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.module.PlayLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 15/02/2017.
 */

public class PopularPlayAdapter extends RecyclerView.Adapter<PopularPlayAdapter.PopoularPlayViewHolder> {
    private final Context mContext;
    private final List<PlayModel> mPlayList;
    private final PlayLoadModule mPlayModule;

    public PopularPlayAdapter(Context context, List<PlayModel> playModels) {
        mContext = context;
        mPlayList = playModels;
        mPlayModule = new PlayLoadModule();
    }

    @Override
    public PopularPlayAdapter.PopoularPlayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.popular_play_list_item, parent, false);
        return new PopularPlayAdapter.PopoularPlayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularPlayAdapter.PopoularPlayViewHolder holder, int position) {
        PlayModel play = mPlayList.get(position);
        holder.setPopularPlay(play);
    }

    @Override
    public int getItemCount() {
        if (mPlayList == null)
            return 0;
        else
            return mPlayList.size();
    }

    class PopoularPlayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_popular_play)
        protected ImageView mPopularPlayImageView;
        @BindView(R.id.tv_popular_play_date)
        protected TextView mPopularPlayDateTextView;
        @BindView(R.id.tv_popular_play_place)
        protected TextView mPopularPlayPlaceTextView;

        private PlayModel mPopularPlay;

        public PopoularPlayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setDate(String startDate, String endDate) {
            mPopularPlayDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mPopularPlayPlaceTextView.setText(place);
        }

        public void setPopularPlay(final PlayModel exhibition) {
            mPopularPlay = exhibition;
            Glide.with(mContext).load(mPopularPlay.getPlayPicture())
                    .thumbnail(0.1f)
                    .override(650,650)
                    .into(mPopularPlayImageView);
            setDate(mPopularPlay.getStartDate(), mPopularPlay.getEndDate());
            setPlace(mPopularPlay.getPlayPlace());
        }

        @Override
        public void onClick(View v) {
            mPlayModule.addClickNumToPlay(mPopularPlay.getPlayId(), new PlayService.addClickNumCallback() {
                @Override
                public void success() {
                    Log.v("daisy", "add success");
                }
                @Override
                public void error(Throwable throwable) {

                }
            });
            Intent startDetailActivity = new Intent(mContext, DetailPlayActivity.class);
            startDetailActivity.putExtra("Exhibition", mPopularPlay);
            mContext.startActivity(startDetailActivity);
        }
    }
}
