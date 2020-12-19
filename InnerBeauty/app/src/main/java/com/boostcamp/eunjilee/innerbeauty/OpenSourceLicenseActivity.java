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

import java.io.IOException;
import java.io.InputStream;
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
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_android_compatibility_v4_name),OpenLicenseContents(getString(R.string.license_android_compatibility_v4_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_android_compatibility_v7_name),OpenLicenseContents(getString(R.string.license_android_compatibility_v7_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_android_design_support_name),OpenLicenseContents(getString(R.string.license_android_design_support_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_butter_knife_name),OpenLicenseContents(getString(R.string.license_butter_knife_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_glide_name),OpenLicenseContents(getString(R.string.license_glide_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_okhttp_name),OpenLicenseContents(getString(R.string.license_okhttp_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_retrofit_name),OpenLicenseContents(getString(R.string.license_retrofit_name_txt))));
        mOpenSourceLicenseModelList.add(new OpenSourceLicenseModel(getString(R.string.license_ted_permission_name),OpenLicenseContents(getString(R.string.license_ted_permission_name_txt))));
    }

    private String OpenLicenseContents(String libraryName){
        String licenseContents="";
        try {
            InputStream inputStream = getAssets().open(libraryName + ".txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            licenseContents = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return licenseContents;
    }
}
