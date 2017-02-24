package com.boostcamp.eunjilee.innerbeauty;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;

import com.boostcamp.eunjilee.innerbeauty.adapter.SettingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.rv_setting)
    protected RecyclerView mSettingRecyclerView;
    private List<String> mSettingList;
    private SettingAdapter mSettingAdapter;
    private UserSharedPreference mUserPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        initRecyclerView();
        loadSettingList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        mSettingList = new ArrayList<>();
        mSettingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSettingAdapter = new SettingAdapter(this, mSettingList);
        mSettingRecyclerView.setAdapter(mSettingAdapter);
    }
    private void loadSettingList(){
        mSettingList.add("앱 버전");
        mSettingList.add("오픈소스 라이브러리");
        mSettingAdapter.notifyDataSetChanged();
    }
}
