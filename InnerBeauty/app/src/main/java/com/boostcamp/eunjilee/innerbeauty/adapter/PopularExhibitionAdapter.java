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
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 15/02/2017.
 */

public class PopularExhibitionAdapter extends RecyclerView.Adapter<PopularExhibitionAdapter.PopoularExhibitionViewHolder> {
    private final Context mContext;
    private final List<ExhibitionModel> mExhibitionList;
    private final ExhibitionLoadModule mExhibitionModule;

    public PopularExhibitionAdapter(Context context, List<ExhibitionModel> exhibitionModels) {
        mContext = context;
        mExhibitionList = exhibitionModels;
        mExhibitionModule = new ExhibitionLoadModule();
    }

    @Override
    public PopularExhibitionAdapter.PopoularExhibitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.popular_exhibition_list_item, parent, false);
        return new PopularExhibitionAdapter.PopoularExhibitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularExhibitionAdapter.PopoularExhibitionViewHolder holder, int position) {
        ExhibitionModel exhibition = mExhibitionList.get(position);
        holder.setPopularExhibition(exhibition);
    }

    @Override
    public int getItemCount() {
        if (mExhibitionList == null)
            return 0;
        else
            return mExhibitionList.size();
    }

    class PopoularExhibitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_popular_exhibition)
        protected ImageView mPopularExhibitionImageView;
        @BindView(R.id.tv_popular_exhibition_date)
        protected TextView mPopularExhibitionDateTextView;
        @BindView(R.id.tv_popular_exhibition_place)
        protected TextView mPopularExhibitionPlaceTextView;

        private ExhibitionModel mPopularExhibition;

        public PopoularExhibitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setDate(String startDate, String endDate) {
            mPopularExhibitionDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mPopularExhibitionPlaceTextView.setText(place);
        }

        public void setPopularExhibition(final ExhibitionModel exhibition) {
            mPopularExhibition = exhibition;
            Glide.with(mContext).load(mPopularExhibition.getExhibitionPicture())
                    .thumbnail(0.1f)
                    .override(650,650)
                    .into(mPopularExhibitionImageView);
            setDate(mPopularExhibition.getStartDate(), mPopularExhibition.getEndDate());
            setPlace(mPopularExhibition.getExhibitionPlace());
        }

        @Override
        public void onClick(View v) {
            mExhibitionModule.addClickNumToExhibition(mPopularExhibition.getExhibitionId(), new ExhibitionService.addClickNumCallback() {
                @Override
                public void success() {
                    Log.v("daisy", "add success");
                }
                @Override
                public void error(Throwable throwable) {

                }
            });
            Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
            startDetailActivity.putExtra("Exhibition", mPopularExhibition);
            mContext.startActivity(startDetailActivity);
        }
    }
}
