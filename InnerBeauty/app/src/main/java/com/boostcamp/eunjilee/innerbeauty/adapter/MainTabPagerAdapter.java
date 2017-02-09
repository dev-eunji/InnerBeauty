package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.boostcamp.eunjilee.innerbeauty.fragment.ExhibitionFragment;
import com.boostcamp.eunjilee.innerbeauty.fragment.PlayFragment;
import com.boostcamp.eunjilee.innerbeauty.fragment.PopularFragment;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class MainTabPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public MainTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        // Returns the current tab
        switch (position) {
            case 0:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;
            case 1:
                ExhibitionFragment exhibitionFragment = new ExhibitionFragment();
                return exhibitionFragment;
            case 2:
                PlayFragment playFragment = new PlayFragment();
                return playFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
