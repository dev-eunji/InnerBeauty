package com.boostcamp.eunjilee.innerbeauty.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.adapter.ExhibitionAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExhibitionFragment extends Fragment {
    private static final int PAGE = 1;

    @BindView(R.id.rv_exhibition)
    protected RecyclerView mExhibitionRecyclerView;

    private List<ExhibitionModel> mExhibitionList;
    private ExhibitionAdapter mExhibitionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibition, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        loadExhibitionList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mExhibitionAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mExhibitionList = new ArrayList<>();
        mExhibitionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)); // 2 items in a vertical line.
        mExhibitionAdapter = new ExhibitionAdapter(getActivity(), mExhibitionList);
        mExhibitionRecyclerView.setAdapter(mExhibitionAdapter);
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private void loadExhibitionList() {
        ExhibitionLoadModule exhibitionLoadModule = new ExhibitionLoadModule();
        //noinspection AccessStaticViaInstance,AccessStaticViaInstance,AccessStaticViaInstance
        exhibitionLoadModule.getExhibitionListByAsync(PAGE, new ExhibitionService.getExhibitionListCallback() {
            @Override
            public void success(List<ExhibitionModel> exhibitionModelList) {
                mExhibitionList.clear();
                mExhibitionList.addAll(exhibitionModelList);
                mExhibitionAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }
}
