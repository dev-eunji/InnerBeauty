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
import com.boostcamp.eunjilee.innerbeauty.adapter.ExhibitionAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.module.ExhibitionLoadModule;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExhibitionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExhibitionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExhibitionFragment extends Fragment {
    @BindView(R.id.rv_exhibition)
    RecyclerView mExhibitionRecyclerView;

    private List<ExhibitionModel> mExhibitionList;
    private ExhibitionAdapter mExhibitionAdapter;
    private ExhibitionLoadModule mExhibitionLoadModule;
    private int PAGE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibition, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        loadExhibitionList();
        return view;
    }

    private void initRecyclerView() {
        mExhibitionList = new ArrayList<ExhibitionModel>();
        mExhibitionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)); // 2 items in a vertical line.
        mExhibitionAdapter = new ExhibitionAdapter(getActivity(), mExhibitionList);
        mExhibitionRecyclerView.setAdapter(mExhibitionAdapter);
    }

    private void loadExhibitionList() {
        mExhibitionLoadModule = new ExhibitionLoadModule();
        mExhibitionLoadModule.getExhibitionListByAsync(PAGE, new ExhibitionService.getExhibitionListCallback() {
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
