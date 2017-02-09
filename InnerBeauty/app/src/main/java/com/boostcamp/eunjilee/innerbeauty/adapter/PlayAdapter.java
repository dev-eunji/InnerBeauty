package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.DetailContentActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.PlayViewHolder> {
    private Context mContext;
    private List<PlayModel> mPlayList;

    public PlayAdapter(Context context, List<PlayModel> playModels) {
        mContext = context;
        mPlayList = playModels;
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
        ImageView mPlayImageView;
        @BindView(R.id.tv_play_date)
        TextView mPlaytDateTextView;
        @BindView(R.id.tv_play_place)
        TextView mPlayPlaceTextView;

        public PlayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setDate(String date) {
            mPlaytDateTextView.setText(date);
        }

        private void setPlace(String place) {
            mPlayPlaceTextView.setText(place);
        }

        public void setPlay(PlayModel play) {
            setDate(play.getStartDate()); // TODO : start~end로 바꾸기
            setPlace(play.getPlayPlace());
            Glide.with(mContext).load(play.getPlayPicture()).into(mPlayImageView);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            //int clickedContentId = Integer.parseInt(memoIdTextView.getText().toString());
            Intent startDetailActivity = new Intent(mContext, DetailContentActivity.class);
            Bundle bundle = new Bundle();
            //bundle.putInt("ContentId", clickedItemId);
            startDetailActivity.putExtras(bundle);
            mContext.startActivity(startDetailActivity);
        }
    }
}
