package com.boostcamp.eunjilee.innerbeauty.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.adapter.PlayAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.module.PlayLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlayFragment extends Fragment {
    private static final int PAGE = 1;

    @BindView(R.id.rv_play)
    protected RecyclerView mPlayRecyclerView;

    private List<PlayModel> mPlayList;
    private PlayAdapter mPlayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        loadPlayList();
        return view;
    }

    private void initRecyclerView() {
        mPlayList = new ArrayList<>();
        mPlayRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)); // 2 items in a vertical line.
        mPlayAdapter = new PlayAdapter(getActivity(), mPlayList);
        mPlayRecyclerView.setAdapter(mPlayAdapter);
    }

    private void loadPlayList() {
        PlayLoadModule playLoadModule = new PlayLoadModule();
        playLoadModule.getPlayListByAsync(PAGE, new PlayService.getPlayListCallback() {
            @Override
            public void success(List<PlayModel> playModelList) {
                mPlayList.clear();
                mPlayList.addAll(playModelList);
                mPlayAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }
}
