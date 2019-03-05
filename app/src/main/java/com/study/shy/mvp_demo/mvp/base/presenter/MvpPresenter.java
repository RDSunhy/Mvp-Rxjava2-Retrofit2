package com.study.shy.mvp_demo.mvp.base.presenter;

import com.study.shy.mvp_demo.mvp.base.view.MvpView;

public interface MvpPresenter<V extends MvpView> {
    /**
     * 绑定视图
     *
     * @param view
     */
    public void attachView(V view);

    /**
     * 解除绑定
     */
    public void dettachView();
}
