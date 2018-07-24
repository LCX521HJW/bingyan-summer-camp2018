package com.summer.bingyan.gitpopular.base_and_contract;

import com.summer.bingyan.gitpopular.base_and_contract.BasePresenter;
import com.summer.bingyan.gitpopular.base_and_contract.BaseView;

import java.util.List;

public interface FavoriteContract {
    interface Presenter extends BasePresenter {
        void start_popular();
        void start_trend();
    }
    interface View extends BaseView<Presenter>
    {
        void PopularListChanged(List list);
        void TrendListChanged(List list);
        void showToast();
    }
}
