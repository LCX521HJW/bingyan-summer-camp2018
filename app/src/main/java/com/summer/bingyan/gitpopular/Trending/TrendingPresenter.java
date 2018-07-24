package com.summer.bingyan.gitpopular.Trending;

import android.content.Context;

import com.summer.bingyan.gitpopular.base_and_contract.Contract;

import java.util.ArrayList;
import java.util.List;

public class TrendingPresenter implements Contract.Prsenter{
    private Contract.View trendingView;
    private Context context;
    private List<Trend> trends=new ArrayList<>();
    private TrendingModel trendingModel;
    public TrendingPresenter(Context context, Contract.View trendingView){
        this.context=context;
        this.trendingView=trendingView;
        trendingView.setPresenter(this);
        createModel();
    }
    public void createModel(){
        trendingModel=new TrendingModel(context, new TrendingModel.TrendingCallback() {
            @Override
            public void onSuccess(List list) {
                trendingView.dismissDialog();
                trends.clear();
                trends.addAll(list);
                trendingView.ListChanged(trends);
            }
            @Override
            public void onFail() {
                trendingView.showToast();
            }
        });
    }
    public void start(String url){
        trendingView.showDialog();
       trendingModel.getTrending(url);
    }
    public void refresh(String url)
    {
        trendingModel.getTrending(url);
    }
}
