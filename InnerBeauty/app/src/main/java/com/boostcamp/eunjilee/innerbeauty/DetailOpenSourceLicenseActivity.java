package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.model.OpenSourceLicenseModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOpenSourceLicenseActivity extends AppCompatActivity {
    @BindView(R.id.tv_opensource_library_detail)
    protected TextView mOpenSourceLibraryDetailTextView;
    private OpenSourceLicenseModel mOpenSourceLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_open_source_license);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Intent intent = getIntent();
        mOpenSourceLicense = (OpenSourceLicenseModel) intent.getSerializableExtra("OpenSourceLibrary");
        actionBar.setTitle(mOpenSourceLicense.getLibraryName());
        showOpenSourceLibraryDetail();
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

    private void showOpenSourceLibraryDetail(){
        mOpenSourceLibraryDetailTextView.setText(mOpenSourceLicense.getLibraryLicense());
    }

}
