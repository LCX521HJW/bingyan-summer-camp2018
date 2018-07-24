package com.summer.bingyan.gitpopular.favorite;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.summer.bingyan.gitpopular.base_and_contract.FavoriteContract;
import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.adapters.PopularAdapter;
import com.summer.bingyan.gitpopular.adapters.TrendingAdapter;
import com.summer.bingyan.gitpopular.popular.Popular;
import com.summer.bingyan.gitpopular.Trending.Trend;
import com.summer.bingyan.gitpopular.setting.SettingFragment;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteContract.View {
    private TrendingAdapter trendingAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager;
    private List<Trend>trends=new ArrayList<>();
    private List<Popular>populars=new ArrayList<>();
    private MyDatabaseHelper myDatabaseHelper;
    private TextView trend_black;
    private TextView trend_color;
    private TextView popular_black;
    private TextView popular_color;
    private Toolbar toolbar;
    private FavoriteContract.Presenter favoritePrsenter;
    private PopularAdapter popularAdapter;
    public FavoriteFragment() {
    }
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
        switch (SettingFragment.theme_selected)
        {
            case 0:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Red);
                break;
            case 1:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
                break;
            case 2:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.green);
                break;
            case 3:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Purple);
                break;
            case 4:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Cyan);
                break;
            case 5:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Yellow);
        }
        LayoutInflater themeinflater=inflater.cloneInContext(contexttheme);
        View view=themeinflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.show_favorite);
        trend_black=(TextView)view.findViewById(R.id.trend_favorite_black);
        trend_color=(TextView)view.findViewById(R.id.trend_favorite_color);
        toolbar=(Toolbar)view.findViewById(R.id.favorite_toolbar);
        toolbar.setTitle("Favorite");
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        popular_black=(TextView)view.findViewById(R.id.popular_favorite_black);
        popular_color=(TextView)view.findViewById(R.id.popular_favorite_color);
        trend_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(trendingAdapter);
                favoritePrsenter.start_trend();
                trend_color.setVisibility(View.VISIBLE);
                trend_black.setVisibility(View.INVISIBLE);
                popular_black.setVisibility(View.VISIBLE);
                popular_color.setVisibility(View.INVISIBLE);
            }
        });
        popular_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(popularAdapter);
                favoritePrsenter.start_popular();
               trend_black.setVisibility(View.VISIBLE);
               trend_color.setVisibility(View.INVISIBLE);
               popular_black.setVisibility(View.INVISIBLE);
               popular_color.setVisibility(View.VISIBLE);
            }
        });
        trend_color.setVisibility(View.VISIBLE);
        trend_black.setVisibility(View.INVISIBLE);
        popular_black.setVisibility(View.VISIBLE);
        popular_color.setVisibility(View.INVISIBLE);
        layoutManager=new  StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Context context=getActivity();
        myDatabaseHelper=new MyDatabaseHelper(context,"Git.db",null,2);
        myDatabaseHelper.getWritableDatabase();
        FavoritePresenter presenter=new FavoritePresenter(context,myDatabaseHelper,this);
        trendingAdapter=new TrendingAdapter(context,recyclerView,trends);
        popularAdapter=new PopularAdapter(context,recyclerView,populars);
        recyclerView.setAdapter(trendingAdapter);
        trendingAdapter.notifyDataSetChanged();
        favoritePrsenter.start_trend();
        return view;
    }
    public void setPresenter(FavoriteContract.Presenter favoritePresenter)
    {
        this.favoritePrsenter=favoritePresenter;
    }
    public void TrendListChanged(List list)
    {
        trendingAdapter.trendListChanged(list);
        trendingAdapter.notifyDataSetChanged();
    }
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    public void PopularListChanged(List list)
    {
       popularAdapter.popularListChanged(list);
        popularAdapter.notifyDataSetChanged();
    }
    public void showToast()
    {
    }
}
