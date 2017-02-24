package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.DetailOpenSourceLicenseActivity;
import com.boostcamp.eunjilee.innerbeauty.OpenSourceLicenseActivity;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.OpenSourceLicenseModel;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eunjilee on 24/02/2017.
 */

public class OpenSourceLicenseAdapter extends RecyclerView.Adapter<OpenSourceLicenseAdapter.OpenSourceLicenseViewHolder> {
    private final Context mContext;
    private final List<OpenSourceLicenseModel> mOpenSourceLicenseModelsList;

    public OpenSourceLicenseAdapter(Context context,List<OpenSourceLicenseModel> openSourceLicenseModelsList) {
        mContext = context;
        mOpenSourceLicenseModelsList = openSourceLicenseModelsList;
    }

    @Override
    public OpenSourceLicenseAdapter.OpenSourceLicenseViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.opensource_library_list_item, parent, false);
        return new OpenSourceLicenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OpenSourceLicenseAdapter.OpenSourceLicenseViewHolder holder,int position) {
        OpenSourceLicenseModel openSourceLicenseModel = mOpenSourceLicenseModelsList.get(position);
        holder.setOpenSourceLibrary(openSourceLicenseModel);
    }

    @Override
    public int getItemCount() {
        if(mOpenSourceLicenseModelsList==null){
            return 0;
        } else{
            return mOpenSourceLicenseModelsList.size();
        }
    }

    class OpenSourceLicenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_opensource_library_name)
        protected TextView mOpenSourceLibraryNameTextView;
        private OpenSourceLicenseModel mOpenSourceLicenseModel;

        public OpenSourceLicenseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        private void setName(String name){
            mOpenSourceLibraryNameTextView.setText(name);
        }

        public void setOpenSourceLibrary(final OpenSourceLicenseModel openSourceLicenseModel) {
            mOpenSourceLicenseModel = openSourceLicenseModel;
            setName(mOpenSourceLicenseModel.getLibraryName());
        }

        @Override
        public void onClick(View v) {
            Intent startDetailActivity = new Intent(mContext, DetailOpenSourceLicenseActivity.class);
            startDetailActivity.putExtra("OpenSourceLibrary", mOpenSourceLicenseModel);
            mContext.startActivity(startDetailActivity);
        }
    }
}