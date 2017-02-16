package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.eunjilee.innerbeauty.adapter.MainTabPagerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int SNS_TYPE_NAVER = 1;
    private static final int SNS_TYPE_FACEBOOK = 2;
    private static final int SYS_TYPE_KAKAO = 3;

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.nav_view)
    protected NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.main_tab_layout)
    protected TabLayout mTabLayout;
    @BindView(R.id.main_view_papger)
    protected ViewPager mViewPager;

    private UserSharedPreference mUserSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        View header = mNavigationView.getHeaderView(0);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(header);

        mUserSharedPreference = new UserSharedPreference(this);

        initTabLayout();
        initViewPager();
        headerViewHolder.initNavHeaderMain();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_popular));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_exhibition));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_play));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void initViewPager() {
        MainTabPagerAdapter pagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {
            Intent startMyFavoriteContentsActivity = new Intent(this, MyFavoriteContentsActivity.class);
            startActivity(startMyFavoriteContentsActivity);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected class HeaderViewHolder {
        @BindView(R.id.imgv_user_profile)
        protected ImageView mUserProfileImageView;
        @BindView(R.id.tv_user_name)
        protected TextView mUserNameTextView;
        @BindView(R.id.tv_user_email)
        protected TextView mUserEmailTextView;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        private void initNavHeaderMain() {
            Glide.with(MainActivity.this).load(mUserSharedPreference.getUserProfileImage())
                    .centerCrop()
                    .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                    .into(mUserProfileImageView);
            mUserNameTextView.setText(String.valueOf(mUserSharedPreference.getUserName()));
            if (mUserSharedPreference.getUserSnsType() == SNS_TYPE_FACEBOOK) {
                mUserEmailTextView.setText(mUserSharedPreference.getUserEmail());
            }
        }
    }
}