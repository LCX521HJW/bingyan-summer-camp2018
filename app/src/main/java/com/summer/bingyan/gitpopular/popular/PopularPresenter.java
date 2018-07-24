package com.summer.bingyan.gitpopular.popular;

import android.content.Context;

import com.summer.bingyan.gitpopular.base_and_contract.Contract;

import java.util.ArrayList;
import java.util.List;

public class PopularPresenter implements Contract.Prsenter {
    private Contract.View popularView;
    private PopularModel popularModel;
    private Context context;
    private List<Popular> populars = new ArrayList<>();

    public PopularPresenter(Context context, Contract.View popularView) {
        this.popularView = popularView;
        this.context = context;
        popularView.setPresenter(this);
        createModel();
    }

    public void createModel()
    {
        popularModel=new PopularModel(context,new PopularModel.PopularCallback() {
            @Override
            public void onSuccess(List list) {
                populars.clear();
                populars.addAll(list);
                popularView.dismissDialog();
                popularView.ListChanged(list);
                if (list.size()==0)
                {
                    popularView.showToast();
                }
            }
            public void onFail()
            {
                popularView.showToast();
            }
        });
    }
    public void start(String url)
    {
        popularView.showDialog();
        popularModel.getPopular(url);
    }
    public void refresh(String url)
    {
        popularModel.getPopular(url);
    }

}
