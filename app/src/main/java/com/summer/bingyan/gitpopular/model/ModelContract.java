package com.summer.bingyan.gitpopular.model;

import java.util.List;

public interface ModelContract {
    interface CallBack {
        void onSuccess(List list);
        void onFail();
    }
    void start_fromInternet(String string,CallBack callBack);
    void start_fromJson(String string,CallBack callBack);
    void 
}
