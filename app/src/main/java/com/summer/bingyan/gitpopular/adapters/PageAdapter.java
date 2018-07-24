package com.summer.bingyan.gitpopular.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.summer.bingyan.gitpopular.favorite.FavoriteFragment;
import com.summer.bingyan.gitpopular.popular.PopularFragment;
import com.summer.bingyan.gitpopular.setting.SettingFragment;
import com.summer.bingyan.gitpopular.Trending.TrendingFragment;
import com.summer.bingyan.gitpopular.utils.FragmentGnerator;

import java.util.HashMap;

public class PageAdapter extends FragmentPagerAdapter {
    private int num;
    private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();
    private Fragment[]fragments;
    public PageAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        return createFragment(position);
    }

    @Override
    public int getCount() {
        return num;
    }

    private Fragment createFragment(int pos) {
        Fragment fragment = mFragmentHashMap.get(pos);
        fragments= FragmentGnerator.getFragements();
        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment =fragments[0];
                    break;
                case 1:
                    fragment = fragments[1];
                    break;
                case 2:
                    fragment =fragments[2];
                    break;
                case 3:
                    fragment = fragments[3];
                    break;
            }
            mFragmentHashMap.put(pos, fragment);
        }
        return fragment;
    }
}
