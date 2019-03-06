package com.study.shy.mvp_demo.mvp.activity.demo;

import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.bean.TestBean;
import com.study.shy.mvp_demo.mvp.base.view.MvpBaseView;
import com.study.shy.mvp_demo.mvp.base.view.MvpView;

public interface DemoView extends MvpBaseView {

    void getImageResult(AcgImg bean);

    void onFailed(String msg);
}

