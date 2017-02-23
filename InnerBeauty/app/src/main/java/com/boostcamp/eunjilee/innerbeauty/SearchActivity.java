package com.boostcamp.eunjilee.innerbeauty;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.boostcamp.eunjilee.innerbeauty.adapter.SearchContentsAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.SearchContentsModel;
import com.boostcamp.eunjilee.innerbeauty.module.SearchModule;
import com.boostcamp.eunjilee.innerbeauty.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.edt_search)
    protected EditText mSearchEditText;
    @BindView(R.id.rv_search_contents)
    protected RecyclerView mSearchRecyclerView;

    private List<SearchContentsModel> mSearchContentsList;
    private SearchContentsAdapter mSearchContentsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initRecyclerView();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchContents();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        mSearchEditText.addTextChangedListener(textWatcher);
    }

    @OnClick(R.id.btn_search)
    void searchContents(){
        SearchModule searchModule = new SearchModule();
        //noinspection AccessStaticViaInstance,AccessStaticViaInstance,AccessStaticViaInstance
        searchModule.searchContentsList(mSearchEditText.getText().toString(), new SearchService
                .searchContentsListCallback() {
            @Override
            public void success(List<SearchContentsModel> contentsModelList) {
                mSearchContentsList.clear();
                mSearchContentsList.addAll(contentsModelList);
                mSearchContentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Throwable throwable) {
            }
        });
    }

    private void initRecyclerView(){
        mSearchContentsList = new ArrayList<>();
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchContentsAdapter = new SearchContentsAdapter(this, mSearchContentsList);
        mSearchRecyclerView.setAdapter(mSearchContentsAdapter);
    }
}
