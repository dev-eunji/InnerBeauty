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
    private final int mTabCount;

    public MainTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        // Returns the current tab
        switch (position) {
            case 0:
                return new PopularFragment();
            case 1:
                return new ExhibitionFragment();
            case 2:
                return new PlayFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
