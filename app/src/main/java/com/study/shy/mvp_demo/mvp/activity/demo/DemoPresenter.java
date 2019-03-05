package com.study.shy.mvp_demo.mvp.activity.demo;

import android.content.Context;

import com.study.shy.mvp_demo.mvp.base.presenter.MvpBasePresenter;

public class DemoPresenter extends MvpBasePresenter<DemoView> {

    private DemoModel mDemoModel;

    public DemoPresenter(Context context) {
        super(context);
        mDemoModel = new DemoModel();
    }
}
