package com.summer.bingyan.gitpopular.base_and_contract;
//Trend 和Popular的Contract
import com.summer.bingyan.gitpopular.base_and_contract.BasePresenter;
import com.summer.bingyan.gitpopular.base_and_contract.BaseView;

import java.util.List;

public interface Contract {
    interface View extends BaseView<Prsenter> {
        void ListChanged(List list);
        void showToast();
        void showDialog();
        void dismissDialog();
    }
    interface Prsenter extends BasePresenter{
        void refresh(String string);
    }
}
