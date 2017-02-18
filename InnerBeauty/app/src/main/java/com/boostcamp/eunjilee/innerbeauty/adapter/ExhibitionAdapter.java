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
import com.boostcamp.eunjilee.innerbeauty.UserSharedPreference;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.module.ContentsModule;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ExhibitionViewHolder> {
    private static final int EXHIBITION_TYPE=1;
    private final Context mContext;
    private final List<ExhibitionModel> mExhibitionList;

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

        private UserSharedPreference mUserSharedPreference;
        private ContentsModule mContentsModule;

        public ExhibitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

            //TODO : Like를 어떻게 관리할지 결정하기
            mUserSharedPreference = new UserSharedPreference(mContext);
            mContentsModule = new ContentsModule();

        }

        private void setDate(String startDate, String endDate) {
            mExhibitionDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mExhibitionPlaceTextView.setText(place);
        }

        public void setExhibition(final ExhibitionModel exhibition) {
            mExhibition = exhibition;
            //int sCorner = 50;
            //int sMargin = 0;

            Glide.with(mContext).load(mExhibition.getExhibitionPicture())
                    .thumbnail(0.1f)
                    //.bitmapTransform(new RoundedCornersTransformation( mContext,sCorner, sMargin))
                    .into(mExhibitionImageView);
            setDate(mExhibition.getStartDate(), mExhibition.getEndDate());
            setPlace(mExhibition.getExhibitionPlace());
            mLikeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // TODO; contentsId가 계속 null인 문제가 있다. 해결이 필요
                        // TODO; LIKE테이블에 userId, contentType, contentId 저장하기
                        mContentsModule.registerFavoriteContents(mUserSharedPreference.getUserId(), mExhibition.getExhibitionId(),EXHIBITION_TYPE, new ContentsService.registerFavoriteContentsCallback(){
                            @Override
                            public void success() {
                                Toast.makeText(mContext, "unlike->like", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void error(Throwable throwable) {

                            }
                        });
                    } else {
                        // TODO; LIKE테이블에 userId, contentType, contentId 삭제하기
                        mContentsModule.deleteFavoriteContents(mUserSharedPreference.getUserId(), exhibition.getExhibitionId(),EXHIBITION_TYPE, new ContentsService.deleteFavoriteContentsCallback(){
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
            Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
            startDetailActivity.putExtra("Exhibition", mExhibition);
            mContext.startActivity(startDetailActivity);
        }
    }
}
