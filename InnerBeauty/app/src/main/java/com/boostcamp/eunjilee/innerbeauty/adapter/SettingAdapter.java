package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.OpenSourceLicenseActivity;
import com.boostcamp.eunjilee.innerbeauty.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 24/02/2017.
 */

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private final Context mContext;
    private final List<String> mSettingList;

    public SettingAdapter(Context context, List<String> settingList) {
        mContext = context;
        mSettingList = settingList;
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.setting_list_item, parent, false);
        return new SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingViewHolder holder, int position) {
        String settingTitle = mSettingList.get(position);
        holder.setSettingTitle(settingTitle);
    }

    @Override
    public int getItemCount() {
        if (mSettingList == null)
            return 0;
        else
            return mSettingList.size();
    }

    class SettingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_setting_title)
        protected TextView mSettingTextView;


        public SettingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setSettingTitle(final String settingTitle) {
            mSettingTextView.setText(settingTitle);
        }

        @Override
        public void onClick(View v) {
            if(mSettingTextView.getText().toString().equals("오픈소스 라이브러리")){
                Intent startDetailActivity = new Intent(mContext, OpenSourceLicenseActivity.class);
                mContext.startActivity(startDetailActivity);
            }
        }
    }
}