package com.boostcamp.eunjilee.innerbeauty.adapter;

import static com.boostcamp.eunjilee.innerbeauty.Constant.EXHIBITION_TYPE;
import static com.boostcamp.eunjilee.innerbeauty.Constant.PLAY_TYPE;

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
import com.boostcamp.eunjilee.innerbeauty.model.SearchContentsModel;
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

public class SearchContentsAdapter extends RecyclerView.Adapter<SearchContentsAdapter.SearchContentsViewHolder> {
    private final Context mContext;
    private final List<SearchContentsModel> mSearchContentsList;

    public SearchContentsAdapter(Context context, List<SearchContentsModel> searchContentsModels) {
        mContext = context;
        mSearchContentsList = searchContentsModels;
    }

    @Override
    public SearchContentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.search_contents_list_item, parent, false);
        return new SearchContentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchContentsViewHolder holder, int position) {
        SearchContentsModel searchContents = mSearchContentsList.get(position);
        holder.setMyFavoriteContents(searchContents);
    }

    @Override
    public int getItemCount() {
        if (mSearchContentsList == null)
            return 0;
        else
            return mSearchContentsList.size();
    }

    class SearchContentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_search_contents)
        protected ImageView mSearchContentsContentsImageView;
        @BindView(R.id.tv_search_contents_title)
        protected TextView mSearchContentsTitleTextView;
        @BindView(R.id.tv_search_contents_date)
        protected TextView mSearchContentsDateTextView;
        @BindView(R.id.tv_search_contentss_place)
        protected TextView mSearchContentsPlaceTextView;

        private SearchContentsModel mSearchContents;
        private ExhibitionModel mExhibition;
        private PlayModel mPlay;

        public SearchContentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        private void setTitle(String title){
            mSearchContentsTitleTextView.setText(title);
        }
        private void setDate(String startDate, String endDate) {
            mSearchContentsDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mSearchContentsPlaceTextView.setText(place);
        }

        public void setMyFavoriteContents(final SearchContentsModel searchContents) {
            mSearchContents = searchContents;
            int contentId = mSearchContents.getContentsId();
            int contentType = mSearchContents.getContentsType();
            if(contentType == EXHIBITION_TYPE){
                ExhibitionLoadModule.getExhibitionByAsync(contentId, new ExhibitionService.getExhibitionCallback() {
                    @Override
                    public void success(ExhibitionModel exhibitionModel) {
                        mExhibition = exhibitionModel;
                        Glide.with(mContext).load(mExhibition.getExhibitionPicture())
                                .thumbnail(0.1f)
                                .into(mSearchContentsContentsImageView);
                        setTitle(mExhibition.getExhibitionTitle());
                        setDate(mExhibition.getStartDate(), mExhibition.getEndDate());
                        setPlace(mExhibition.getExhibitionPlace());
                    }

                    @Override
                    public void error(Throwable throwable) {
                        Log.v("error", "setSearchContents:EXHIBITION_TYPE");
                    }
                });
            } else if(contentType == PLAY_TYPE){
                PlayLoadModule.getPlayByAsync(contentId, new PlayService.getPlayCallback(){
                    @Override
                    public void success(PlayModel playModel) {
                        mPlay = playModel;
                        Glide.with(mContext).load(mPlay.getPlayPicture())
                                .thumbnail(0.1f)
                                .into(mSearchContentsContentsImageView);
                        setTitle(mPlay.getPlayTitle());
                        setDate(mPlay.getStartDate(), mPlay.getEndDate());
                        setPlace(mPlay.getPlayPlace());
                    }

                    @Override
                    public void error(Throwable throwable) {
                        Log.v("error", "setSearchContents:PLAY_TYPE");
                    }
                });
            }
        }

        @Override
        public void onClick(View v) {
            if(mSearchContents.getContentsType() == EXHIBITION_TYPE){
                Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
                startDetailActivity.putExtra("Exhibition", mExhibition);
                mContext.startActivity(startDetailActivity);
            }else if(mSearchContents.getContentsType() == PLAY_TYPE){
                Intent startDetailActivity = new Intent(mContext, DetailPlayActivity.class);
                startDetailActivity.putExtra("Play", mPlay);
                mContext.startActivity(startDetailActivity);
            }

        }
    }
}