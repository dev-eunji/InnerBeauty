package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.boostcamp.eunjilee.innerbeauty.DetailExhibitionActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.RoundedCornersTransformation;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.DetailContentActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ExhibitionViewHolder> {
    private Context mContext;
    private List<ExhibitionModel> mExhibitionList;

    public ExhibitionAdapter(Context context, List<ExhibitionModel> exhibitionModels) {
        mContext = context;
        mExhibitionList = exhibitionModels;
    }

    @Override
    public ExhibitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.exhibition_list_item, parent, false);
        return new ExhibitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExhibitionViewHolder holder, int position) {
        ExhibitionModel exhibition = mExhibitionList.get(position);
        holder.setExhibition(exhibition);
    }

    @Override
    public int getItemCount() {
        if (mExhibitionList == null)
            return 0;
        else
            return mExhibitionList.size();
    }

    class ExhibitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgv_list_exhibition)
        protected ImageView mExhibitionImageView;
        @BindView(R.id.tv_exhibition_date)
        protected TextView mExhibitionDateTextView;
        @BindView(R.id.tv_exhibition_place)
        protected TextView mExhibitionPlaceTextView;
        @BindView(R.id.btn_like)
        protected ToggleButton mLikeBtn;

        private ExhibitionModel mExhibition;
        private boolean mIsLike;

        public ExhibitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

            //TODO : Like를 어떻게 관리할지 결정하기
            mIsLike = true;
            mLikeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Toast.makeText(mContext, "unlike->like", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "like->unlike", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private void setDate(String startDate, String endDate) {
            mExhibitionDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mExhibitionPlaceTextView.setText(place);
        }

        public void setExhibition(ExhibitionModel exhibition) {
            mExhibition = exhibition;
            int sCorner = 50;
            int sMargin = 0;

            Glide.with(mContext).load(exhibition.getExhibitionPicture())
                    .thumbnail(0.1f)
            //        .bitmapTransform(new RoundedCornersTransformation( mContext,sCorner, sMargin))
                    .into(mExhibitionImageView);
            setDate(exhibition.getStartDate(), exhibition.getEndDate());
            setPlace(exhibition.getExhibitionPlace());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
            startDetailActivity.putExtra("Exhibition", mExhibition);
            mContext.startActivity(startDetailActivity);
        }
    }
}
