package com.boostcamp.eunjilee.innerbeauty.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.adapter.ExhibitionAdapter;
import com.boostcamp.eunjilee.innerbeauty.adapter.PopularExhibitionAdapter;
import com.boostcamp.eunjilee.innerbeauty.adapter.PopularPlayAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.module.PlayLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularFragment extends Fragment {
    private static final int PAGE = 1;

    @BindView(R.id.rv_popular_exhibition)
    protected RecyclerView mPopularExhibitionRecyclerView;
    @BindView(R.id.rv_popular_play)
    protected RecyclerView mPopularPlayRecyclerView;

    private List<ExhibitionModel> mPopularExhibitionList;
    private PopularExhibitionAdapter mPopularExhibitionAdapter;
    private List<PlayModel> mPopularPlayList;
    private PopularPlayAdapter mPopularPlayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        loadExhibitionList();
        loadPlayList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPopularExhibitionAdapter.notifyDataSetChanged();
        mPopularPlayAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mPopularExhibitionList = new ArrayList<>();
        mPopularExhibitionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        mPopularExhibitionAdapter = new PopularExhibitionAdapter(getActivity(), mPopularExhibitionList);
        mPopularExhibitionRecyclerView.setAdapter(mPopularExhibitionAdapter);

        mPopularPlayList = new ArrayList<>();
        mPopularPlayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mPopularPlayAdapter = new PopularPlayAdapter(getActivity(), mPopularPlayList);
        mPopularPlayRecyclerView.setAdapter(mPopularPlayAdapter);
    }

    private void loadExhibitionList() {
        ExhibitionLoadModule exhibitionLoadModule = new ExhibitionLoadModule();
        exhibitionLoadModule.getGlobalFavoriteExhibitionList(new ExhibitionService.getGlobalFavoriteExhibitionListCallback() {
            @Override
            public void success(List<ExhibitionModel> exhibitionModelList) {
                mPopularExhibitionList.clear();
                mPopularExhibitionList.addAll(exhibitionModelList);
                mPopularExhibitionAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }
    private void loadPlayList(){
        PlayLoadModule playLoadModule = new PlayLoadModule();
        playLoadModule.getGlobalFavoritePlayList(new PlayService.getGlobalFavoritePlayListCallback() {
            @Override
            public void success(List<PlayModel> playModelList) {
                mPopularPlayList.clear();
                mPopularPlayList.addAll(playModelList);
                mPopularPlayAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }
}
