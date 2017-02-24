package com.boostcamp.eunjilee.innerbeauty;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.boostcamp.eunjilee.innerbeauty.adapter.OpenSourceLicenseAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.OpenSourceLicenseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenSourceLicenseActivity extends AppCompatActivity {
    @BindView(R.id.rv_opensourse_license)
    protected RecyclerView mOpenSourceLicenseRecyclerView;
    private List<OpenSourceLicenseModel> mOpenSourceLicenseModelList;
    private OpenSourceLicenseAdapter mOpenSourceLicenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source_license);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        initOpenSourceLicenseModelList();
        initRecyclerView();
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

    private void initRecyclerView(){
        mOpenSourceLicenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOpenSourceLicenseAdapter = new OpenSourceLicenseAdapter(this, mOpenSourceLicenseModelList);
        mOpenSourceLicenseRecyclerView.setAdapter(mOpenSourceLicenseAdapter);
    }
    private void initOpenSourceLicenseModelList(){
        mOpenSourceLicenseModelList = new ArrayList<>();
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_butter_knife_name),getString(R.string.license_butter_knife)));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_glide_name),getString(R.string.license_glide)));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_retrofit_name),getString(R.string.license_retrofit)));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_ted_permission_name),getString(R.string.license_ted_permission)));

    }
}
