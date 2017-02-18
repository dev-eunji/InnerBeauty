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

import com.boostcamp.eunjilee.innerbeauty.DetailExhibitionActivity;
import com.boostcamp.eunjilee.innerbeauty.DetailPlayActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.module.PlayLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 15/02/2017.
 */

public class MyFavoriteContentsAdapter extends RecyclerView.Adapter<MyFavoriteContentsAdapter.MyFavoriteContentsViewHolder> {
    private static final int EXHIBITION_TYPE=1;
    private static final int PLAY_TYPE=2;

    private final Context mContext;
    private final List<FavoriteContentsModel> mMyFavoriteContentsList;

    public MyFavoriteContentsAdapter(Context context, List<FavoriteContentsModel> favoriteContentsModels) {
        mContext = context;
        mMyFavoriteContentsList = favoriteContentsModels;
    }

    @Override
    public MyFavoriteContentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_favorite_contents_list_item, parent, false);
        return new MyFavoriteContentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFavoriteContentsViewHolder holder, int position) {
        FavoriteContentsModel favoriteContents = mMyFavoriteContentsList.get(position);
        holder.setMyFavoriteContents(favoriteContents);
    }

    @Override
    public int getItemCount() {
        if (mMyFavoriteContentsList == null)
            return 0;
        else
            return mMyFavoriteContentsList.size();
    }

    class MyFavoriteContentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_my_favorite_contents)
        protected ImageView mMyFavoriteContentsContentsImageView;
        @BindView(R.id.tv_my_favorite_contents_title)
        protected TextView mMyFavoriteContentsTitleTextView;
        @BindView(R.id.tv_my_favorite_contents_date)
        protected TextView mMyFavoriteContentsDateTextView;
        @BindView(R.id.tv_my_favorite_contents_place)
        protected TextView mMyFavoriteContentsPlaceTextView;

        private FavoriteContentsModel mMyFavoriteContents;
        private ExhibitionModel mExhibition;
        private PlayModel mPlay;

        public MyFavoriteContentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        private void setTitle(String title){
            mMyFavoriteContentsTitleTextView.setText(title);
        }
        private void setDate(String startDate, String endDate) {
            mMyFavoriteContentsDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mMyFavoriteContentsPlaceTextView.setText(place);
        }

        public void setMyFavoriteContents(final FavoriteContentsModel favoriteContents) {
            mMyFavoriteContents = favoriteContents;
            int contentId = mMyFavoriteContents.getContentsId();
            int contentType = mMyFavoriteContents.getContentsType();
            if(contentType == EXHIBITION_TYPE){
                ExhibitionLoadModule.getExhibitionByAsync(contentId, new ExhibitionService.getExhibitionCallback() {
                    @Override
                    public void success(ExhibitionModel exhibitionModel) {
                        mExhibition = exhibitionModel;
                        Glide.with(mContext).load(mExhibition.getExhibitionPicture())
                                .thumbnail(0.1f)
                                .into(mMyFavoriteContentsContentsImageView);
                        setTitle(mExhibition.getExhibitionTitle());
                        setDate(mExhibition.getStartDate(), mExhibition.getEndDate());
                        setPlace(mExhibition.getExhibitionPlace());
                    }

                    @Override
                    public void error(Throwable throwable) {
                        Log.v("error", "setMyFavoriteContents:EXHIBITION_TYPE");
                    }
                });
            } else if(contentType == PLAY_TYPE){
                PlayLoadModule.getPlayByAsync(contentId, new PlayService.getPlayCallback(){
                    @Override
                    public void success(PlayModel playModel) {
                        mPlay = playModel;
                        Glide.with(mContext).load(mPlay.getPlayPicture())
                                .thumbnail(0.1f)
                                .into(mMyFavoriteContentsContentsImageView);
                        setTitle(mPlay.getPlayTitle());
                        setDate(mPlay.getStartDate(), mPlay.getEndDate());
                        setPlace(mPlay.getPlayPlace());
                    }

                    @Override
                    public void error(Throwable throwable) {

                    }
                });
            }
        }

        @Override
        public void onClick(View v) {
            if(mMyFavoriteContents.getContentsType() == EXHIBITION_TYPE){
                Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
                startDetailActivity.putExtra("Exhibition", mExhibition);
                mContext.startActivity(startDetailActivity);
            }else if(mMyFavoriteContents.getContentsType() == PLAY_TYPE){
                Intent startDetailActivity = new Intent(mContext, DetailPlayActivity.class);
                startDetailActivity.putExtra("Play", mPlay);
                mContext.startActivity(startDetailActivity);
            }

        }
    }
}