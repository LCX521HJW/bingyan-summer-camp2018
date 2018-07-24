package com.summer.bingyan.gitpopular.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.summer.bingyan.gitpopular.R;

import com.summer.bingyan.gitpopular.favorite.FavoriteFragment;
import com.summer.bingyan.gitpopular.popular.PopularFragment;
import com.summer.bingyan.gitpopular.setting.SettingFragment;
import com.summer.bingyan.gitpopular.Trending.TrendingFragment;

public class FragmentGnerator {
    public static final String []mTabTitle=new String[]{"trending","popular","favorite","setting"};
    public static final int []mTabImage=new int[]{R.drawable.trending,R.drawable.popular,R.drawable.favorite,R.drawable.setting};
    public static Fragment[] getFragements()
    {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = TrendingFragment.newInstance();
        fragments[1] = PopularFragment.newInstance();
        fragments[2] = FavoriteFragment.newInstance();
        fragments[3] = SettingFragment.newInstance();
        return fragments;
    }
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_image);
        tabIcon.setImageResource(mTabImage[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
