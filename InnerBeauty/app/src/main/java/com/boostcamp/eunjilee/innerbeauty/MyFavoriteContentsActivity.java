package com.boostcamp.eunjilee.innerbeauty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.adapter.MyFavoriteContentsAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.module.ContentsModule;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteContentsActivity extends AppCompatActivity {

    @BindView(R.id.rv_my_favorite_contents)
    protected RecyclerView mMyFavoriteRecyclerView;

    private List<FavoriteContentsModel> mMyFavoriteContentsList;
    private MyFavoriteContentsAdapter mMyFavoriteContentsAdapter;

    private UserSharedPreference mUserPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_contents);
        ButterKnife.bind(this);
        mUserPreference = new UserSharedPreference(this);
        initRecyclerView();
        loadMyFavoriteContents();
    }

    private void initRecyclerView(){
        mMyFavoriteContentsList = new ArrayList<>();
        mMyFavoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMyFavoriteContentsAdapter = new MyFavoriteContentsAdapter(this, mMyFavoriteContentsList);
        mMyFavoriteRecyclerView.setAdapter(mMyFavoriteContentsAdapter);
    }
    private void loadMyFavoriteContents(){
        ContentsModule contentsLoadModule = new ContentsModule();
        //noinspection AccessStaticViaInstance,AccessStaticViaInstance,AccessStaticViaInstance
        contentsLoadModule.getFavoriteContentsList(mUserPreference.getUserId(), new ContentsService.getFavoriteContentsListCallback() {
            @Override
            public void success(List<FavoriteContentsModel> favoriteContentList) {
                mMyFavoriteContentsList.clear();
                mMyFavoriteContentsList.addAll(favoriteContentList);
                mMyFavoriteContentsAdapter.notifyDataSetChanged();
            }
            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }
}
