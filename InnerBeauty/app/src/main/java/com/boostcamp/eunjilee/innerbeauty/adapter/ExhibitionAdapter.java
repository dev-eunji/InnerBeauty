package com.boostcamp.eunjilee.innerbeauty.adapter;

import static com.boostcamp.eunjilee.innerbeauty.Constant.EXHIBITION_TYPE;

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

import com.boostcamp.eunjilee.innerbeauty.DetailExhibitionActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.UserSharedPreference;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.module.ContentsModule;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ExhibitionViewHolder> {
    private final Context mContext;
    private final List<ExhibitionModel> mExhibitionList;
    private final ContentsModule mContentsModule;
    private UserSharedPreference mUserSharedPreference;

    public ExhibitionAdapter(Context context, List<ExhibitionModel> exhibitionModels) {
        mContext = context;
        mExhibitionList = exhibitionModels;
        mContentsModule = new ContentsModule();
        mUserSharedPreference = new UserSharedPreference(mContext);

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
        @BindView(R.id.tv_exhibition_title)
        protected TextView mExhibitionTitleTextView;
        @BindView(R.id.tv_exhibition_date)
        protected TextView mExhibitionDateTextView;
        @BindView(R.id.tv_exhibition_place)
        protected TextView mExhibitionPlaceTextView;
        @BindView(R.id.btn_like_exhibition)
        protected ToggleButton mLikeBtn;

      private ExhibitionModel mExhibition;

      public ExhibitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setTitle(String title) {
            mExhibitionTitleTextView.setText(title);
        }

        private void setDate(String startDate, String endDate) {
            mExhibitionDateTextView.setText(startDate + " ~ " + endDate);
        }

        private void setPlace(String place) {
            mExhibitionPlaceTextView.setText(place);
        }

        public void setExhibition(final ExhibitionModel exhibition) {
            mExhibition = exhibition;

            Glide.with(mContext).load(mExhibition.getExhibitionPicture())
                    .thumbnail(0.1f)
                    .into(mExhibitionImageView);
            setTitle(mExhibition.getExhibitionTitle());
            setDate(mExhibition.getStartDate(), mExhibition.getEndDate());
            setPlace(mExhibition.getExhibitionPlace());
            setLikeBtn();
            mLikeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mContentsModule.registerFavoriteContents(mUserSharedPreference.getUserId(), mExhibition.getExhibitionId(),EXHIBITION_TYPE, new ContentsService.registerFavoriteContentsCallback(){
                            @Override
                            public void success() {
                                Snackbar.make(mExhibitionImageView, R.string.snb_add_favorite_exhibition_success, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void error(Throwable throwable) {
                                Snackbar.make(mExhibitionImageView, R.string.snb_add_favorite_exhibition_fail, Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        mContentsModule.deleteFavoriteContents(mUserSharedPreference.getUserId(), exhibition.getExhibitionId(),EXHIBITION_TYPE, new ContentsService.deleteFavoriteContentsCallback(){
                            @Override
                            public void success() {
                                Snackbar.make(mExhibitionImageView, R.string.snb_delete_favorite_exhibition_success, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void error(Throwable throwable) {
                                 Snackbar.make(mExhibitionImageView, R.string.snb_delete_favorite_exhibition_fail, Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        private void setLikeBtn(){
            ContentsModule.getFavoriteContentsListByContentsType(mUserSharedPreference.getUserId(), EXHIBITION_TYPE, new ContentsService.getFavoriteContentsListCallback() {
                @Override
                public void success(List<FavoriteContentsModel> favoriteContentsModel) {
                    for(int i=0; i<favoriteContentsModel.size(); i++) {
                        if (favoriteContentsModel.get(i).getContentsId() == mExhibition.getExhibitionId())
                            mLikeBtn.setChecked(true);
                    }
                }

                @Override
                public void error(Throwable throwable) {
                    Log.v("error", "setLikeBtn");
                }
            });
        }
        @Override
        public void onClick(View v) {
            ExhibitionLoadModule.addClickNumToExhibition(mExhibition.getExhibitionId(), new ExhibitionService.addClickNumCallback() {
                @Override
                public void success() {
                    Log.v("daisy", "add success");
                }
                @Override
                public void error(Throwable throwable) {

                }
            });
            Intent startDetailActivity = new Intent(mContext, DetailExhibitionActivity.class);
            startDetailActivity.putExtra("Exhibition", mExhibition);
            mContext.startActivity(startDetailActivity);
        }
    }
}
