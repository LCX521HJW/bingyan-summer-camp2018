package com.summer.bingyan.gitpopular.favorite;

import android.content.Context;

import com.summer.bingyan.gitpopular.base_and_contract.FavoriteContract;
import com.summer.bingyan.gitpopular.popular.Popular;
import com.summer.bingyan.gitpopular.Trending.Trend;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {
    private List<Trend> trends = new ArrayList<>();
    private List<Popular>populars=new ArrayList<>();
    private MyDatabaseHelper myDatabaseHelper;
    private FavoriteModel favoriteModel;
    private Context context;
    private FavoriteContract.View favoriteView;
    public FavoritePresenter(Context context, MyDatabaseHelper myDatabaseHelper, FavoriteContract.View favoriteView)
    {
        this.favoriteView=favoriteView;
        this.context=context;
        this.myDatabaseHelper=myDatabaseHelper;
        favoriteView.setPresenter(this);
    }
    public  void start_trend()
    {
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                trends.clear();
                trends.addAll(list);
                for (Trend trend:trends)
                {
                    trend.setFavorite(true);
                }
                favoriteView.TrendListChanged(trends);
            }
        },myDatabaseHelper);
        favoriteModel.query_trend();
    }
    public void start_popular()
    {
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                populars.clear();
                populars.addAll(list);
                for (Popular popular:populars)
                {
                    popular.setFavorite(true);
                }
                favoriteView.PopularListChanged(populars);
            }
        },myDatabaseHelper);
        favoriteModel.query_popular();
    }
    public void start(String string)
    {
}
}
